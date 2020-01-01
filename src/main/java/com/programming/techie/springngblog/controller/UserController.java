package com.programming.techie.springngblog.controller;

import com.programming.techie.springngblog.dto.RegisterRequest;
import com.programming.techie.springngblog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/saveUserId")
    public ResponseEntity signup(@RequestBody RegisterRequest registerRequest) {
        //User signup = authService.signup(registerRequest);
        //return new ResponseEntity(signup, HttpStatus.OK);
        return null;
    }
}
