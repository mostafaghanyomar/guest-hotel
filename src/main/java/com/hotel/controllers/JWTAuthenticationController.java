package com.hotel.controllers;

import com.hotel.exception.BadRequestException;
import com.hotel.dto.JWTAuthCredentialsDTO;
import com.hotel.services.JWTAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mostafa
 * @email mostafa.ghany.omar@gmail.com
 */
@RestController
@RequestMapping("/auth")
public class JWTAuthenticationController {

    @Autowired
    private JWTAuthenticationService jwtAuthenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JWTAuthCredentialsDTO jwtAuthCredentialsDTO) throws AuthenticationException {
        return ResponseEntity.ok(jwtAuthenticationService.login(jwtAuthCredentialsDTO));
    }

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshAndGetBackAuthenticationToken(HttpServletRequest request) throws BadRequestException {
        return ResponseEntity.ok(jwtAuthenticationService.refreshAndGetBackAuthenticationToken(request));
    }
}
