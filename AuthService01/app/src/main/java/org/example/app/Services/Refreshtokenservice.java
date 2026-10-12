package org.example.app.Services;
// this ios the refreh token service  that basiclly injects the token right naa

import lombok.RequiredArgsConstructor;
import org.example.app.Repository.Refreshtoken;
import org.example.app.Repository.UserDetailsRepo;
import org.example.app.entities.RefreshToken;

import org.example.app.entities.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service


public class Refreshtokenservice {


Refreshtoken RefreshtokenRepository;


UserDetailsRepo userdetailsRepository;

public Refreshtokenservice(Refreshtoken RefreshtokenRepository,
                           UserDetailsRepo userdetailsRepository){


    this.RefreshtokenRepository=RefreshtokenRepository;
    this.userdetailsRepository=userdetailsRepository;
}


public RefreshToken createRefreshToken(String username){



    User userdetailsextracted=userdetailsRepository.findByUsername(username);
// o rinstead of builder we need to use
    RefreshToken newRefreshtoken=RefreshToken.builder()
            .user(userdetailsextracted)
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().plusMillis(6000000))
            .build();

    return RefreshtokenRepository.save(newRefreshtoken);
}


public RefreshToken verifyExpiration(RefreshToken token){


    if(token.getExpiryDate().compareTo(Instant.now())<0){

        RefreshtokenRepository.delete(token);
        throw new RuntimeException(token.getToken() +" RefrfeshToken expired please login ");
    }
    return token;
}

//
  public Optional<RefreshToken> findByToken(String token){

    return RefreshtokenRepository.findByToken(token);



  }
}
