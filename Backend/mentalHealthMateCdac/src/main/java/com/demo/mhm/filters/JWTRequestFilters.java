package com.demo.mhm.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.demo.mhm.jwt_utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTRequestFilters extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization"); //here we will save the authorization filter
        if (header != null && header.startsWith("Bearer ")) {
            //we are getting the token out of the request header. 
            String token = header.substring(7);
            if (jwtUtils.validateJwtToken(token)) {
                String userName = jwtUtils.getUsernameFromToken(token);

                if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    //load user details from userdetailsService
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                    //create user authentication object
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), 
                    userDetails.getPassword(), userDetails.getAuthorities());

                    //set details in auth object
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        // TODO Auto-generated method stub
        filterChain.doFilter(request, response);
    }

}
