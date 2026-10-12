package org.example.app.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class Jwt {

// THE SECRET KEY

    public static final String SECRET="qwertyuiop12345678903edcvbgt54erfghjio98765r4tyui";
// TO EXTRACT USERNAME FROM THE Token
    public String extractUsername(String token ){
        return extractClaim(token,Claims::getSubject);



    }
// TO EXTRACT WHAT IS THE EXPIRATION OF THE TOKEN
    public Date extractExpiration(String token){

        return extractClaim(token,Claims::getExpiration);


    }
    //using the generic type because we dont know hat type the user will return  will be adjusted on the base wjhat the function would return
    // Function<Claims,T>claimsResolver:::: Also known as Functional Interface ......it basically here it will take input claims and output would be generic type
    //	•	Instead of writing 10 different methods like getUsernameFromToken(), getExpirationFromToken(), getIssuerFromToken(), you write one generic method.
    //	•	This is DRY (Don’t Repeat Yourself) — a key enterprise principle.
    //	•	It makes your code scalable: tomorrow, if business wants new claims like “user role” or “organizationId,” you don’t rewrite methods, you just pass a different function.
    public <T> T  extractClaim(String token , Function<Claims,T>claimsResolver){

        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);


    }

    private Claims extractAllClaims(String token){



        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();

    }

    private Boolean isTokenExpired(String token){


        return extractExpiration(token).before(new Date());
    }


    public Boolean validateToken(String token, UserDetails userDetails){
        final String username=extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));



    }
public String  GenerateToken(String username){

        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,username);


}
    private String createToken(Map<String,Object> claims,String  username){

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date (System.currentTimeMillis()+1000*60*1))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();






    }
    private Key getSignKey(){

        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);

    }

}
