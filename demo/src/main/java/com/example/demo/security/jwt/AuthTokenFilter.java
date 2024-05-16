package com.example.demo.security.jwt;

import com.example.demo.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwt=parseJwt(request);

            if(jwt!=null && jwtUtils.validateJwtToken(jwt)){
                String userName=jwtUtils.getUserNameFromJwtToken(jwt);

                UserDetails userDetails=userDetailsService.loadUserByUsername(userName);

                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            System.err.println("Cannot Set Auth");
        }

        filterChain.doFilter(request,response);
    }

    // neglecting "Bearer" word from the token
    private String parseJwt(HttpServletRequest request){
        String authHeader=request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader)&& authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }

        return null;
    }
}
