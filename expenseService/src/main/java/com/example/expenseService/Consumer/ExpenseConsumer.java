package com.example.expenseService.Consumer;

import com.example.expenseService.Dto.ExpenseDto;
import com.example.expenseService.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ExpenseConsumer {

    private  ExpenseService expenseService;
@Autowired
    ExpenseConsumer(ExpenseService expenseService){


        this.expenseService=expenseService;
    }


    @KafkaListener(topics="${spring.kafka.topic-name.json}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto dto){

        try{

expenseService.createExepense(dto);

        }catch(Exception e){

System.out.println("Exception in listening the kafka not able to consume the event ");
            e.printStackTrace();
        }





    }
}
