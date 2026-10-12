package com.example.userService.Controllers;

import com.example.userService.Repository.UserRepository;
import com.example.userService.Service.UserService;
import com.example.userService.entities.UserInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserController {

   private   UserRepository userrepo;

   private UserService userService;

// in this usercontroller the apsi should be  first fetch the userdetails other things need to change some useredetails

@GetMapping("/user/{userId}")
public UserInfoDto userDetails(@PathVariable String userId){

return userrepo.findById(userId).orElseThrow(()->new RuntimeException("User Not Found"));





}


}
