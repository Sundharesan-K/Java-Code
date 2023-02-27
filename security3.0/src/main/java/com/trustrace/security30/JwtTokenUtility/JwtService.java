package com.trustrace.security30.JwtTokenUtility;

import com.trustrace.security30.pojo.User;
import com.trustrace.security30.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtService {
    @Autowired
    private UserRepository repository;
    private final static String SECRET="6250655368566D5971337436773979244226452948404D635166546A576E5A72";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    public<T> T extractClaim(String token, Function<Claims,T> claimResolver){
        final Claims claims=extractAllClaims(token);
        return claimResolver.apply (claims);
    }
    private Claims extractAllClaims(String token){
        try {
            return Jwts
                    .parserBuilder ()
                    .setSigningKey (getSignKey ())
                    .build ()
                    .parseClaimsJws (token)
                    .getBody ();
        }catch (ExpiredJwtException e){
            throw new RuntimeException ("JWT token expired");
        }
    }
    private Boolean isTokenExpired(String token){
        return extractExpiration (token).before (new Date ());
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username=extractUsername (token);
        return (username.equals (userDetails.getUsername ())&&!isTokenExpired (token));
    }


    public String generateToken(String username){
        Map<String , Object> claims=new HashMap<> ();
        return createToken(claims,username);
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder ()
                .setClaims (claims)
                .setSubject (username)
                .setIssuedAt (new Date (System.currentTimeMillis ()))
                .setExpiration (new Date (System.currentTimeMillis ()+1000 * 60 * 30))
                .signWith (getSignKey(), SignatureAlgorithm.HS256)
                .compact ();
    }

    private Key getSignKey() {
        byte[] keyBytes= Decoders.BASE64.decode (SECRET);
        return Keys.hmacShaKeyFor (keyBytes);
    }
    public String ValidateTokens(String token){
        try{
            final Claims claims=extractAllClaims (token);
            User user=repository.findByUserName (claims.getSubject ());
            if (claims.get ("roles").equals ("user") && user != null && !isTokenExpired (token)){
                return "user";
            }
            else if (claims.get ("roles").equals ("admin") && user != null && !isTokenExpired (token)){
                return "admin";
            }
            else {
                return "";
            }
        }
        catch (IllegalArgumentException | MalformedJwtException e) {
            return "";
        }
    }
}
