package com.jencruz.taskapplication.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jencruz.taskapplication.service.JwtService;
import com.jencruz.taskapplication.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
//    private final JwtService jwtService;
//    private final UserService userService;
//
//    public JwtAuthorizationFilter(JwtService jwtService, UserService userService) {
//        this.jwtService = jwtService;
//        this.userService = userService;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        if (request.getServletPath().contains("/login")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String header = request.getHeader("Authorization");
//        if (header == null || !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        String token = header.split(" ")[1];
//        String username = jwtService.extractUsername(token);
//        User user = userService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
//                user.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
        filterChain.doFilter(request, response);
    }
}
