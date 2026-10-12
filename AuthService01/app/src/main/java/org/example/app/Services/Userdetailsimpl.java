package org.example.app.Services;

import lombok.*;
import org.example.app.Dto.UserDto;
import org.example.app.Repository.UserDetailsRepo;
import org.example.app.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;





@Data
@Component
@RequiredArgsConstructor
public class Userdetailsimpl implements UserDetailsService {

    private final PasswordEncoder passwordEncrypt;

  private  final UserDetailsRepo  userRepository;



    @Override
public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException{

User user=userRepository.findByUsername(Username);


if(user == null){
    throw new UsernameNotFoundException("No such username found here");
}
        System.out.println("Loaded user: " + user.getUsername());
        System.out.println("Stored encoded password: " + user.getPassword());
return new Userdetails(user);



}





public User  CheckIfUserAlreadyExist(UserDto us){

    return userRepository.findByUsername(us.getUsername());
}

// basically all the steps how the user will sign up
public Boolean signupUser(UserDto us){
    // add validation , add email , ad


    us.setPassword(passwordEncrypt.encode(us.getPassword()));

    if(Objects.nonNull(CheckIfUserAlreadyExist(us))){

        return false;
    }

    String userId= UUID.randomUUID().toString(); // this make sthe user_id null as not passing to the user
User usersss=User.builder()
        .userId(userId)
        .username(us.getUsername())
        .password(us.getPassword())
        .email(us.getEmail())
        .phoneNumber(us.getPhoneNumber())
        .roles(new HashSet<>())
        .build();

    userRepository.save(usersss);



return true;
}



public User fetchCurrentUser(UserDto uss) {


  User mylove= userRepository.findByUsername(uss.getUsername());




return mylove;
}



}



