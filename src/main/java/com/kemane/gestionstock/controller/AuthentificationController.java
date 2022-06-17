package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.auth.AuthenticationResponse;
import com.kemane.gestionstock.dto.auth.AuthentificationRequest;
import com.kemane.gestionstock.model.auth.ExtendedUser;
import com.kemane.gestionstock.service.auth.ApplicationUserDetailsService;
import com.kemane.gestionstock.utils.JwtUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.kemane.gestionstock.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping( "/authenticate")
    public AuthenticationResponse authenticate(@RequestBody AuthentificationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getLogin());

        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

        return AuthenticationResponse.builder().accessToken(jwt).build();
    }
}
