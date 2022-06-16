package com.kemane.gestionstock.controller;

import com.kemane.gestionstock.dto.auth.AuthentificationReponse;
import com.kemane.gestionstock.dto.auth.AuthentificationRequest;
import com.kemane.gestionstock.service.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import static com.kemane.gestionstock.utils.Constants.AUTHENTICATION_ENDPOINT;

@RestController
@RequestMapping(AUTHENTICATION_ENDPOINT)
public class AuthentificationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService applicationUserDetailsService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationReponse> authenticate(@RequestBody AuthentificationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = applicationUserDetailsService.loadUserByUsername(request.getLogin());

        return ResponseEntity.ok(AuthentificationReponse.builder().accessToken("dummy_access_token").build());
    }
}
