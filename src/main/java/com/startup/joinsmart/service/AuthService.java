package com.startup.joinsmart.service;

import com.startup.joinsmart.dto.LoginRequest;
import com.startup.joinsmart.dto.RegisterRequest;
import com.startup.joinsmart.model.User;
import com.startup.joinsmart.repository.UserRepository;
import com.startup.joinsmart.security.JwtProvider;
import com.startup.joinsmart.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;

    public User signup(RegisterRequest registerRequest) {
        if(authUtil.isEmailAlreadyRegistered(registerRequest.getEmail().toLowerCase())) return null;
        User user = new User();
        user.setEmail(registerRequest.getEmail().toLowerCase());
        user.setPassword(encodePassword(registerRequest.getPassword()));
        user.setRoleId(2L);
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());
        user.setActive(true);
        return userRepository.save(user);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail().toLowerCase(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String authenticationToken = jwtProvider.generateToken(authenticate);
        Optional<User> userByEmail = userRepository.findByEmail(loginRequest.getEmail().toLowerCase());

        return new AuthenticationResponse(authenticationToken, loginRequest.getEmail().toLowerCase(), userByEmail.get().getUserRegistrationId(),userByEmail.get().isActive());
    }

    public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        return Optional.of(principal);
    }
}
