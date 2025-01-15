package org.eDrink24.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

// token 생성하는 기능
@Component  // @Service
public class JwtTokenService {
	  
    private final JwtEncoder jwtEncoder;
    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    //Spring Security에서 인증 결과인 Authentication 이용해서 token을 생성하는 메서드
    public String generateToken(Authentication authentication) {
        if (authentication == null) {
            throw new IllegalArgumentException("Authentication cannot be null");
        }

        var scope = authentication // 사용자 권한을 가져옴 
                        .getAuthorities() 
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder() // Jwt에 들어갈 payload(body) 내용을 정의
                        .issuer("SERVER") // 누가 발급했는지
                        .issuedAt(Instant.now()) // 현재시간
                        .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES)) // 90분 이후 만기
                        .subject(authentication.getName()) // 
                        .claim("scope", scope) 
                        .build();
        System.out.println(authentication.getName());
        System.out.println(scope);

        return this.jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }
}
