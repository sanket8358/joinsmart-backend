package com.startup.joinsmart.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String email;
    private Long userRegistrationId;
    private boolean isActive;
}
