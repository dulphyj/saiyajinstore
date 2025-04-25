package com.gonzalodev.saiyajinstore.backend.infrastructure.jwt;

import com.gonzalodev.saiyajinstore.backend.application.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JWTValidate {
    private final JwtProperties jwtProperties;

    public JWTValidate(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public  boolean tokenExists(HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader(jwtProperties.getHeaderAuthorization());
        if(header == null || !header.startsWith(jwtProperties.getTokenBearer())){
            return false;
        }
        return true;
    }

    public Claims JWTValid(HttpServletRequest request){
        String header = request.getHeader(jwtProperties.getHeaderAuthorization().replace(jwtProperties.getTokenBearer(), ""));
        String jwtToken = header.replace(jwtProperties.getTokenBearer(), "").trim();
        return (Claims) Jwts.parser()
                .verifyWith(jwtProperties.getSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }



    public void setAuthentication(Claims claims, CustomUserDetailService customUserDetailService){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }
}
