package com.contactkeeper.security.controller;

import com.contactkeeper.security.JwtUtil;
import com.contactkeeper.security.model.AuthenticationRequest;
import com.contactkeeper.security.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) throws Exception {
        try {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUseraname(), request.getPassword())
        );
        } catch( BadCredentialsException badCredentialsException) {
            throw new Exception("Incorrect Username or Password", badCredentialsException);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUseraname());
        final String jwt =  jwtUtil.generateToken(userDetails, request.getUseraname());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }
}
