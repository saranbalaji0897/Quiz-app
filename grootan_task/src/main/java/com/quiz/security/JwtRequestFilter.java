package com.quiz.security;

import com.quiz.security.jwtutils.JwtTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {



    JwtTokenUtils jwtTokenUtils;

    @Autowired
    JwtRequestFilter(JwtTokenUtils jwtTokenUtils){
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(!request.getServletPath().endsWith("/api/quiz") ){
            return true;
        }else{
            return super.shouldNotFilter(request);
        }

    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        if (httpServletRequest.getServletPath().endsWith("/api/quiz")) {
            String jwtToken = httpServletRequest.getHeader("AUTHORIZATION");
            if (jwtToken != null && jwtToken.length() > 0) {
                if (jwtToken.startsWith("Bearer")) {
                    Authentication auth = jwtToken.startsWith("Bearer%20") ? jwtTokenUtils.validateToken(jwtToken.substring(9)) :jwtTokenUtils.validateToken(jwtToken.substring(7));
                    if (auth != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        filterChain.doFilter(httpServletRequest, httpServletResponse);
                    } else {
                        SecurityContextHolder.getContext().setAuthentication(null);
                        logger.error("Authentication failed");
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                } else {
                    logger.error("Jwt does not contain bearer");
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                }
            } else {
                logger.error("Jwt is not present");
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
//        }
    }


}
