package com.app.Instagram2K24.JWT;

import com.app.Instagram2K24.service.UserUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String emailId = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            emailId = jwtService.getUsernameFromToken(token);
        }
        if (emailId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final var userDetails = userDetailsService.loadUserByUsername(emailId);
            if (jwtService.validateAdminTokenFromUserDetails(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken (userDetails,null,userDetails.getAuthorities ());
                authToken.setDetails (new WebAuthenticationDetailsSource().buildDetails (request));
                SecurityContextHolder.getContext ().setAuthentication (authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
