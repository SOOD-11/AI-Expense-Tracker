package org.example.app.Serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.example.app.Dto.SignupEventDto;
import org.example.app.Dto.UserDto;

import java.util.Map;

public class UserSerializer implements Serializer<SignupEventDto> {





    public void configure(Map<String, ?> configs, boolean isKey) {
    }
/// this will serialize will change the generic object here it it UserDto to string then on to Bytes so the kafka template can produce right



   @Override
    public  byte[] serialize(String var1, SignupEventDto var2){

        byte[ ]  retval=null;
//it will map the object s into other objects if they can be mapped like userdto to user
        ObjectMapper objectMapper=new ObjectMapper();

        try {
            retval=objectMapper.writeValueAsString(var2).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }


       return retval;
   }



  public void close() {
    }
}
