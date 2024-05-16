package com.example.demo.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${app.secret}" )//from spring frameorkd not from lombok
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private int jwtExpiration;

    //generating a key from secret
    private Key key(){ //Key is from java security
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }


    public String generateJwtToken(Authentication authentication){
        UserDetails userDetails= (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpiration))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    //validating token by analysing key
    public boolean validateJwtToken(String authToken){

        try{
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        }catch (MalformedJwtException e){
            System.err.println("Invalid Token");
        }catch (ExpiredJwtException e){
            System.err.println("Expired Token");
        }catch (UnsupportedJwtException e){
            System.err.println("Unsupported token format");
        }catch (IllegalArgumentException e){
            System.err.println("Token blank");
        }

        return false;
    }

    //getting user name from generated token

    public String getUserNameFromJwtToken(String authToken){
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken).getBody().getSubject();
    }
}
