package com.app.Instagram2K24.JWT;

import com.app.Instagram2K24.dao.AdminDao;
import com.app.Instagram2K24.dao.UserDao;
import com.app.Instagram2K24.dto.UserDto;
import com.app.Instagram2K24.model.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService implements Serializable {
    @Autowired
    public AdminDao adminDao;
    @Autowired
    public UserDao userDao;
    private static final long serialVersionUID = -2550185165626007488L;
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateUserFromToken(UserDto userDto, long validity) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDto.getEmailId(), validity);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateAdminFromToken(Admin admin, long validity) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, admin.getEmailId(), validity);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, long validity) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Boolean validateAdminToken(String token) {
        final String username = getUsernameFromToken(token);
        return (!isTokenExpired(token) && adminDao.findAdmin(username) != null);
    }

    public Boolean validateUserToken(String token) {
        final String username = getUsernameFromToken(token);
        return (!isTokenExpired(token) && userDao.findUserFromEmailId(username) != null);
    }

    public Boolean validateAdminTokenFromUserDetails(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

}
