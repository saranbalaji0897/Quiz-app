package com.quiz.security.jwtutils;

import com.quiz.Models.UserModel;
import com.quiz.security.JC;
import com.quiz.security.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
public class JwtTokenUtils implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;



    @Value("${jwt.secret}")
    private String secret;



//retrieve expiration date from jwt token

    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(token, Claims::getExpiration);

    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);

    }

    //for retrieveing any information from token we will need the secret key

    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

    }


//generate token for user

    public String generateToken(User user) {
        log.info("<----generating jwt---->",user.getUserId(),user.getUsername());
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId",user.getUserId());
        claims.put("username",user.getUsername());
        return doGenerateToken(claims);

    }

//while creating the token -

//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID

//2. Sign the JWT using the HS512 algorithm and secret key.

//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)

//   compaction of the JWT to a URL-safe string

    private String doGenerateToken(Map<String, Object> claims) {

        return Jwts.builder().setClaims(claims).setSubject(JC.SUBJECT).setIssuedAt(new Date(System.currentTimeMillis()))

                .setExpiration(new Date(System.currentTimeMillis() + JC.JWT_TOKEN_VALIDITY * 1000))
                .setAudience(JC.AUDIENCE).setIssuer(JC.ISSUER)
                .signWith(SignatureAlgorithm.HS512, JC.SECRET).compact();

    }

//validate token

    public Authentication validateToken(String token) {
        try{
            Claims claims = Jwts.parser().requireAudience(JC.AUDIENCE)
                    .requireSubject(JC.SUBJECT).setSigningKey(JC.SECRET).parseClaimsJws(token).getBody();
            return new UsernamePasswordAuthenticationToken(new User((((Integer) claims.get("userId"))),
                    (String) claims.get("username"),null,true,true,true,true
            ),null);
        }catch ( MalformedJwtException e) {
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.trace("Unsupported JWT token trSecurityExceptionace: {}", e);
        } catch (IllegalArgumentException e) {
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }

        return null;

    }
}
