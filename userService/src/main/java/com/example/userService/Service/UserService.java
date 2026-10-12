package com.example.userService.Service;

import com.example.userService.Repository.UserRepository;
import com.example.userService.entities.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepo;

    // well the service layer will talk directly with repository layer that is the direct database

public  void   CreateOrUpdateUse(UserInfoDto User){


   // first pof all need to check user exist or not



userRepo.save(User);


    }

}







