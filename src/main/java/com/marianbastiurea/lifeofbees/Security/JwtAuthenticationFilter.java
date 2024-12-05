package com.marianbastiurea.lifeofbees.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("=== START doFilterInternal ===");
        System.out.println("Timp: " + java.time.LocalDateTime.now());
        System.out.println("URL cerere: " + request.getRequestURI());
        System.out.println("Metoda HTTP: " + request.getMethod());

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames != null && headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));
        }

        String path = request.getRequestURI();
        if (path.equals("/api/auth/register") || path.equals("/api/auth/login")) {
            System.out.println("Roută publică, se continuă fără validare.");
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            System.out.println("Token extras: " + token);

            if (jwtTokenProvider.isTokenValid(token)) {
                System.out.println("Token este valid.");
                String userId = jwtTokenProvider.extractUsername(token); // Extrage identificatorul utilizatorului
                System.out.println("User ID extras din token: " + userId);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userId, null, List.of()); // Adaugă roluri dacă sunt disponibile
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Autentificare setată pentru utilizator: " + userId);
            } else {
                System.out.println("Token invalid sau expirat.");
            }
        } else {
            System.out.println("Authorization Header este null sau nu începe cu 'Bearer'.");
        }

        filterChain.doFilter(request, response);
        System.out.println("=== END doFilterInternal ===");
    }
}
