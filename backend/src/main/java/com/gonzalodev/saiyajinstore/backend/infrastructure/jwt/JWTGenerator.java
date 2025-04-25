package com.gonzalodev.saiyajinstore.backend.infrastructure.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTGenerator {
    private JwtProperties jwtProperties;

    public JWTGenerator(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String getToken(String username){
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(
                SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().toString()
        );
        String token = Jwts.builder()
                .subject(username)
                .claim("authorities", authorityList.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(jwtProperties.getSignInKey(), Jwts.SIG.HS512).compact();
        return "Bearer "+ token;
    }
}
