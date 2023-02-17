package com.trustrace.Switchenergysystembackend.security;

import com.trustrace.Switchenergysystembackend.entity.UserLogin;
import com.trustrace.Switchenergysystembackend.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.Serializable;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtility implements Serializable {
    @Autowired
    private UserRepository userRepository;
    private static final long serialVersionUID = -2550185165626007488L;
    private final String secret = "secret";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token,Claims::getSubject);
    }
    //retrieve username from jwt token
    public Date getIssuedDateFromToken(String token){
        return getClaimFromToken(token,Claims::getIssuedAt);
    }
    //retrieve expiration from jwt token
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token,Claims::getExpiration);
    }
    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getAllClaimsFromToken(token);
        return claimsResolver.apply (claims);
    }

    private Claims getAllClaimsFromToken(String token){
        try {
            return Jwts.parser ().setSigningKey (secret).parseClaimsJws (token).getBody ();
        }catch (ExpiredJwtException e){
            throw new RuntimeException ("JWT Token Expired");
        }
    }
    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(String name,long jwtTokenValidity) {
        Map<String, Object> claims = new HashMap<> ();
        return doGenerateToken(claims, name,jwtTokenValidity);
    }
    private String doGenerateToken(Map<String, Object> claims, String subject, long jwtTokenValidity) {

        return Jwts.builder().setClaims(claims).claim ("role",userRepository.findByName(subject).get ())
                .setSubject (subject).setIssuedAt (new Date (System.currentTimeMillis ()))
                .setExpiration (new Date (System.currentTimeMillis ()+jwtTokenValidity*1000))
                .signWith (getSignKey(),SignatureAlgorithm.HS512).compact ();
    }

    private Key getSignKey(){
        byte[] keyBytes= Decoders.BASE64.decode (secret);
        return Keys.hmacShaKeyFor (keyBytes);
    }

    public String validateToken(String token){
        try {
            final Claims claims=getAllClaimsFromToken (token);
            Optional<UserLogin> user=userRepository.findByName(claims.getSubject ());
            if (claims.get ("role").equals ("user")&&user!=null&&!isTokenExpired (token)){
                return "user";
            }
            else if (claims.get ("role").equals ("admin")&& user != null && !isTokenExpired (token)){
                return "admin";
            }
            else {
                return "";
            }
        }
        catch (IllegalArgumentException| SignatureException|MalformedJwtException e){
            return "";
        }
    }
}