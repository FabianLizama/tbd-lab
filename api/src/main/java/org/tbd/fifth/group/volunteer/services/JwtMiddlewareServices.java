package org.tbd.fifth.group.volunteer.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.stereotype.Repository;
import org.tbd.fifth.group.volunteer.models.UserModel;
import org.tbd.fifth.group.volunteer.repositories.JwtMiddlewareRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import io.jsonwebtoken.*;


import javax.crypto.SecretKey;
import java.util.Date;


@Repository
public class JwtMiddlewareServices implements JwtMiddlewareRepository {

    private final SecretKey secretKey;

    public JwtMiddlewareServices(SecretKey secretKey){
        this.secretKey = secretKey;
    }

    @Override
    public String generateToken(UserModel userModel){
        Date expiration_date = new Date(System.currentTimeMillis() + 14400000);
        return Jwts
                .builder()
                .claim("id", userModel.getUser_id())
                .claim("username", userModel.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expiration_date)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public Boolean validateToken(String token){
        try{
            Jws<Claims> jws = Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
            return !jws.getPayload().getExpiration().before(new Date());
        }catch(ExpiredJwtException e){
            e.printStackTrace();
            return false;
        }catch(MalformedJwtException e){
            e.printStackTrace();
            return false;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public UserModel decodeJWT(String token){
        Claims claims = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);

        UserModel userModel = new UserModel();
        userModel.setUser_id(id.intValue());
        userModel.setName(username);
        return userModel;
    }
}
