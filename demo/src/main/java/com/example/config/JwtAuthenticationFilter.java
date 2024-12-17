package com.example.config;


import com.example.exption.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;


    private static  String[] AUTH_WHITELIST = {
            "api/login",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/swagger-ui/",
            "/swagger-ui**"
            // other public endpoints of your API may be appended to this array
    };
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")
                    && !request.getRequestURI().equalsIgnoreCase("/swagger-ui.html")
                    && !request.getRequestURI().equalsIgnoreCase("/api/login")
                   ) {
                String jwtToken = authorizationHeader.substring(7);
                String username = null;
                jwtTokenUtil.validateToken(jwtToken);
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    username = jwtTokenUtil.extractUsername(jwtToken);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } else {
//               System.out.println(request.getRequestURI());
//                if (!request.getRequestURI().equalsIgnoreCase("/swagger-ui.html")) {
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//                }
            }
        } catch (CustomException ex) {
            response.sendError(ex.getHttpStatus().value());
            return;
        }

        chain.doFilter(request, response);
    }

}