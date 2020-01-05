package com.startup.joinsmart.controller;

import com.startup.joinsmart.dto.RegisterRequest;
import com.startup.joinsmart.model.UserDetail;
import com.startup.joinsmart.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDetailRepository repo;

    @PostMapping("/saveUserId")
    public ResponseEntity saveUserOneSignalId(@RequestBody RegisterRequest registerRequest) {
        //User signup = authService.signup(registerRequest);
        //return new ResponseEntity(signup, HttpStatus.OK);
        return null;
    }

    @GetMapping("/userDetail/{userId}")
    public UserDetail user(@PathVariable Long userId) {
        Optional<UserDetail> byId = repo.findById(userId);
        if(byId.isPresent())
            return byId.get();
        return null;
    }

    @PostMapping("/userDetail")
    public UserDetail saveUserDetail(@RequestBody UserDetail userDetail) {
        UserDetail user = (UserDetail) repo.save(userDetail);
        if(user!=null)
            return user;
        return null;
    }
}
