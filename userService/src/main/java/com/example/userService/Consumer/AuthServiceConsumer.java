package com.example.userService.Consumer;

import com.example.userService.Repository.UserRepository;
import com.example.userService.Service.UserService;
import com.example.userService.entities.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceConsumer {

private UserRepository userRepo;
private  final UserService userservice;

private UserInfoDto userinfo;

@KafkaListener(topics= "${spring.kafka.topic.name}", groupId= "${spring.kafka.consumer.group-id}")
    public void Listen(UserInfoDto eventData){
try {
 userservice.CreateOrUpdateUse(eventData);
        
    } catch(Exception ex) {
    	
    	ex.printStackTrace();

    	
    	
    	
    	
    	
    	
    }




}



}
