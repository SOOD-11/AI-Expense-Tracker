package org.example.app.Controllers;

import lombok.extern.slf4j.Slf4j;
import org.example.app.Request.AuthRequest;
import org.example.app.Request.RefreshTokenRequest;
import org.example.app.Response.JwtResponse;
import org.example.app.Services.Jwt;
import org.example.app.Services.Refreshtokenservice;
import org.example.app.entities.RefreshToken;
import org.example.app.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j

@RestController
@RequestMapping("/auth/v1")

public class TokenController {
@Autowired
private AuthenticationManager authenticationManager;


@Autowired
private Refreshtokenservice refreshtokenservice;


@Autowired
private Jwt  jwtService;




@PostMapping("/login")
    public ResponseEntity AuthenticateandGetToken(@RequestBody AuthRequest authrequest){
    Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword()));
    if(authentication.isAuthenticated()){

        RefreshToken refreshtoken=refreshtokenservice.createRefreshToken(authrequest.getUsername());
return new ResponseEntity<>(new JwtResponse (jwtService.GenerateToken(authrequest.getUsername()),refreshtoken.getToken() ),HttpStatus.OK);


    }
    else{



        return new ResponseEntity<>("Exception in User service",HttpStatus.INTERNAL_SERVER_ERROR);
    }






}


@PostMapping("/refreshtoken")

public JwtResponse refreshToken(@RequestBody RefreshTokenRequest tokenreq) {
    log.info("Received refresh token request: {}", tokenreq.getToken());

    Optional<RefreshToken> optionalToken = refreshtokenservice.findByToken(tokenreq.getToken());

    if (optionalToken.isEmpty()) {
        log.error("Refresh token {} not found in DB", tokenreq.getToken());
        throw new RuntimeException("Refresh Token is not in DB");
    }

    RefreshToken token = optionalToken.get();
    log.info("Found token in DB: {} with expiry date {}", token.getToken(), token.getExpiryDate());

    try {
        token = refreshtokenservice.verifyExpiration(token);
        log.info("Token {} is valid (not expired)", token.getToken());
    } catch (RuntimeException e) {
        log.error("Token {} has expired", token.getToken());
        throw e; // rethrow so client knows
    }

    User user = token.getUser();
    log.info("Associated user found: {}", user.getUsername());

    String accessToken = jwtService.GenerateToken(user.getUsername());
    log.info("Generated new access token for user {}: {}", user.getUsername(), accessToken);

    return new JwtResponse(accessToken, tokenreq.getToken());
}


}
