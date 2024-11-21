package com.example.nationsleague.controllers;

import com.example.nationsleague.controllers.dto.UserDataDto;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "auth")
public class LoginController {

    @GetMapping(path = "/get-user-data")
    public UserDataDto getUserData() {
        return getCurrentUser()
                .map( UserDataDto::new )
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException( null ));
    }

    public static Optional<UserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return Optional.of( (UserDetails) authentication.getPrincipal() );
        }
        return Optional.empty();
    }

}
