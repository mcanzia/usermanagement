package com.service.usermanagement.security.filters;

import com.service.usermanagement.security.utils.JwtTokenUtil;
import com.service.usermanagement.services.SecurityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter used in Spring security, invoked before UsernamePasswordAuthentication filter
 * Validates that authorization header and JWT security token look correct before proceeding with service callouts
 * Gets user identity from system based on username/email and sets it on Spring security context
 * @author Michael Canziani
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    /** Utility class used for generating and validating security token */
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    /** Security details service used for retrieving user identity*/
    @Autowired
    private SecurityDetailsService securityDetailsService;

    /**
     * Takes in HTTP request and validates that request provides correct authentication headers
     * If authentication succeeds, then user is retrieved by username/email by security details service
     * and set on Spring security context allowing them to perform HTTP callouts in the system
     * @param httpServletRequest HTTP request with authorization header/security token
     * @param httpServletResponse HTTP response after HTTP request is completed
     * @param filterChain set of filters to be performed in order, validate correctness of request
     * @throws ServletException if error occurs connecting through the servlet
     * @throws IOException if error occurs with reading part of the request
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        // First step - Get authorization header
        final String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null && header.startsWith("Bearer ")) {

            // Second step - Get JWT token from header and validate
            final String token = header.split(" ")[1].trim();

            if (jwtTokenUtil.validate(token)) {

                // Third step - Get User identity and set it on spring security
                UserDetails userDetails = securityDetailsService.loadUserByUsername(jwtTokenUtil.getUsername(token));

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }

        // Move onto next filter in filter chain
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
