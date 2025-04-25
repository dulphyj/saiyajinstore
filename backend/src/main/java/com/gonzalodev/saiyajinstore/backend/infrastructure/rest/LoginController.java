package com.gonzalodev.saiyajinstore.backend.infrastructure.rest;

import com.gonzalodev.saiyajinstore.backend.infrastructure.dto.JWTClient;
import com.gonzalodev.saiyajinstore.backend.infrastructure.dto.UserDTO;
import com.gonzalodev.saiyajinstore.backend.infrastructure.jwt.JWTGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
@AllArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<JWTClient> login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.username(), userDTO.password())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.getToken(userDTO.username());
        JWTClient jwtClient = new JWTClient(token);
        return new ResponseEntity<>(jwtClient, HttpStatus.OK);
    }
}
