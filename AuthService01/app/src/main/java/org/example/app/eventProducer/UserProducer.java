package org.example.app.eventProducer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import org.example.app.Dto.SignupEventDto;
import org.example.app.Dto.UserDto;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service


@RequiredArgsConstructor
public class UserProducer {


    private  final KafkaTemplate <String, UserDto> kafkatemplate;

@Value("${spring.kafka.topic.name}")
    private   String topicName;




    public  void  sendEventToKafka(SignupEventDto userinfo){



    Message<SignupEventDto> message= MessageBuilder.withPayload(userinfo).setHeader(KafkaHeaders.TOPIC,topicName).build();
    kafkatemplate.send(message);

    System.out.print(" evemt produced and sent sucessfully"+message+ " topic name "+ topicName);



}
}
