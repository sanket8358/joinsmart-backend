package com.startup.joinsmart.util;

import com.google.gson.JsonObject;
import com.startup.joinsmart.dto.LoginRequest;
import com.startup.joinsmart.dto.RegisterRequest;
import com.startup.joinsmart.model.User;
import com.startup.joinsmart.repository.UserRepository;
import com.startup.joinsmart.service.AuthService;
import com.startup.joinsmart.service.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUtil {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    public boolean isEmailAlreadyRegistered(String email){
        Optional<User> byEmail = userRepository.findByEmail(email);
        return byEmail.isPresent();
    }

    public ResponseEntity checkLinkedInLoginAndSignup(JsonObject userData){
        RegisterRequest user =new RegisterRequest();
        user.setEmail((userData.getAsJsonArray("elements").get(0).getAsJsonObject().getAsJsonObject("handle~").get("emailAddress").getAsString()).toLowerCase());
        if(isEmailAlreadyRegistered(user.getEmail())) {
            LoginRequest req=new LoginRequest();
            req.setEmail(user.getEmail().toLowerCase());
            req.setPassword(userData.get("id").getAsString());
            AuthenticationResponse login = authService.login(req);
            return new ResponseEntity(login, HttpStatus.OK);
        }
        else{
            user.setPassword(userData.get("id").getAsString());
            user.setLastName(userData.get("localizedLastName").getAsString());
            user.setFirstName(userData.get("localizedFirstName").getAsString());
            return new ResponseEntity(authService.signup(user),HttpStatus.OK);
        }

    }
}
