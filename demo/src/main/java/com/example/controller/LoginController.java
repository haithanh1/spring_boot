package com.example.controller;

import com.example.config.JwtTokenUtil;
import com.example.config.TokenHolder;
import com.example.request.LoginRequest;
import com.example.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private TokenHolder tokenHolder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication =null;
        try {
             authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtTokenUtil.generateToken(userDetails);
//            tokenHolder.setToken(jwt); // Save the token
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok(new LoginResponse(jwt, userDetails.getUsername()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            new SecurityContextLogoutHandler().setInvalidateHttpSession(false);
        }
        return "logout"; //You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }
}
