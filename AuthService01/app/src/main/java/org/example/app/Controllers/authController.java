package org.example.app.Controllers;

import org.example.app.Dto.SignupEventDto;
import org.example.app.Dto.UserDto;
import org.example.app.Repository.Refreshtoken;
import org.example.app.Request.AuthRequest;
import org.example.app.Response.JwtResponse;
import org.example.app.Services.Jwt;
import org.example.app.Services.Refreshtokenservice;
import org.example.app.Services.Userdetailsimpl;
import org.example.app.entities.RefreshToken;
import org.example.app.entities.User;
import org.example.app.eventProducer.UserProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
public class authController {


    @Autowired
    private Jwt jwtService;


    @Autowired
   private Refreshtokenservice refreshtokenservice;

@Autowired
    private final  UserProducer UserKafkaProducer;

@Autowired
private Userdetailsimpl userdetailsservice;
    @Autowired
    private UserDetailsService userService;

    public authController(UserProducer userKafkaProducer) {
        UserKafkaProducer = userKafkaProducer;
    }

    @PostMapping("/SignUp")
    public ResponseEntity  SignUp (@RequestBody UserDto userDto){
        try {

            if(userDto.getUsername() ==null){
                return ResponseEntity.badRequest().body("Username is required");
            }

            if(userDto.getPassword()==null ){
                return  ResponseEntity.badRequest().body("Password is required ");
            }

            if(userDto.getEmail()==null){
                return ResponseEntity.badRequest().body("User email is  required");
            }

            if(userDto.getPhoneNumber()==null ){
                return ResponseEntity.badRequest().body("User PhoneNumber  is  required");
            }



            boolean isSignUp=userdetailsservice.signupUser(userDto);
System.out.print("hey request receieved hey request reciedved ");

            if(!isSignUp) {

                return new ResponseEntity<>("Already Exist ", HttpStatus.BAD_REQUEST);
            }
            User UserWithGeneratedId= null;
            try {
                UserWithGeneratedId =userdetailsservice.fetchCurrentUser(userDto);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }



                                SignupEventDto UserDtoWithGeneratedId= SignupEventDto.builder().
                                        userId(UserWithGeneratedId.getUserId()).
                                        username(UserWithGeneratedId.getUsername()).email(UserWithGeneratedId.getEmail())
                                        .phoneNumber(UserWithGeneratedId.getPhoneNumber()).build();



            UserKafkaProducer.sendEventToKafka(UserDtoWithGeneratedId);

         System.out.print("event succsessfully sent to kafka");
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
   return new ResponseEntity<>("Exception in user service",HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }





}
