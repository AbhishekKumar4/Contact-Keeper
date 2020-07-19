package com.contactkeeper.security.filter;

import com.contactkeeper.security.CustomUserService;
/*import com.contactkeeper.security.JwtUtil;*/
import com.contactkeeper.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
import java.util.ArrayList;

@Component
public class JwtTokenVerifierFilter extends OncePerRequestFilter {

    private final static String SECRET = "SECRET_KEY_SECRET_KEY_SECRET_KEY_SECRET_KEY_SECRET_KEY_SECRET_KEY_SECRET_KEY_SECRET_KEY";

    @Autowired
    private CustomUserService customUserService;

/*    @Autowired
    private JwtUtil jwtUtil;*/

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader("Authorization");

        if(isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET.getBytes()))
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
    }

    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.trim().isEmpty())
            return false;
        return true;
    }
}
