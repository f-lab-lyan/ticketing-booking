package pri.roggu.ticketing.user.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import pri.roggu.ticketing.domain.dto.JwtTokenDto;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secret;
    private final long accessTokenValidityInSeconds;
    private final long refreshTokenValidityInSeconds;
    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret
            , @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds
            , @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds
            , UserDetailsService userDetailsService) {
        this.secret = secret;
        this.accessTokenValidityInSeconds = accessTokenValidityInSeconds;
        this.refreshTokenValidityInSeconds = refreshTokenValidityInSeconds;
        this.userDetailsService = userDetailsService;
    }

    /**
     * FUNCTION :: 토큰발급
     * @param userId
     * @return
     */
    public JwtTokenDto generateToken(String userId){
        Claims claims = Jwts.claims().setSubject(userId);
        //claims.put("role", accountAuthGubun);
        long now = new Date().getTime();

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + accessTokenValidityInSeconds))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        String refreshToken = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + refreshTokenValidityInSeconds))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        return JwtTokenDto.builder()
                .userId(userId)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(refreshTokenValidityInSeconds)
                .build();
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserId(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String jwtToken){
        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            return false;
        }
    }

    /**
     * FUNCTION :: AccessToken 만료시간 조회
     * @param accessToken
     * @return
     */
    public Long getExpiration(String accessToken){
        // accessToken 남은 유효시간
        Date expiration = Jwts.parser().setSigningKey(secret).parseClaimsJws(accessToken).getBody().getExpiration();
        // 현재 시간
        long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

}