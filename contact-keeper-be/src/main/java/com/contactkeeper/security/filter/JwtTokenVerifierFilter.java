package com.contactkeeper.security.filter;

import com.contactkeeper.security.CustomUserService;
import com.contactkeeper.security.JwtConfig;
import com.contactkeeper.security.JwtSecretKey;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Component
public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserService customUserService;

    private final JwtConfig jwtConfig;

    private final JwtSecretKey jwtSecretKey;

    public JwtTokenVerifierFilter(JwtConfig jwtConfig, JwtSecretKey jwtSecretKey) {
        this.jwtConfig = jwtConfig;
        this.jwtSecretKey = jwtSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader(jwtConfig.getAuthorizationHeader());

        if(isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(jwtConfig.getTokenPrefix())) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        try {
            String token = authorizationHeader.replace(jwtConfig.getTokenPrefix(), "");
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtSecretKey.getSecretKeyForSigning())
                    .parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            String userName = body.getSubject();

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userName, null, new ArrayList<>()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(JwtException e) {
                throw new IllegalStateException("Token can not be trusted !!!", e);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
}
