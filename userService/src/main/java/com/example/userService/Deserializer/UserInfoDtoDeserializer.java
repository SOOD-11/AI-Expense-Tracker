package com.example.userService.Deserializer;

import com.example.userService.entities.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class UserInfoDtoDeserializer implements Deserializer<UserInfoDto> {

    @Override
    public void configure(Map<String,?> configs, boolean isKey ){

    }

    @Override
    public UserInfoDto deserialize(String arg0, byte[] arg1){

     ObjectMapper    objMap=new ObjectMapper();
     UserInfoDto user=null;
        try{
          user=objMap.readValue(arg1, UserInfoDto.class);

        }catch(Exception ex){

            System.out.println("cannot deserialize");




        }

        return user;

    }



}
