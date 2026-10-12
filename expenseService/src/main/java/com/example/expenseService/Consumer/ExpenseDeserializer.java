package com.example.expenseService.Consumer;

import com.example.expenseService.Dto.ExpenseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;


import java.io.IOException;
import java.util.Map;

public class ExpenseDeserializer implements  Deserializer<ExpenseDto> {

    @Override
    public void close() {
        Deserializer.super.close();
    }
     @Override
     public void configure(Map<String, ?> configs, boolean isKey) {
         Deserializer.super.configure(configs, isKey);
     }

    @Override
    public ExpenseDto deserialize(String s, byte[] bytes) {
        ObjectMapper objmap=new ObjectMapper();
        ExpenseDto expensedto=null;
        try {
            expensedto=objmap.readValue(bytes,ExpenseDto.class);

        } catch (IOException e) {

           e.printStackTrace();
        }

        return expensedto;
    }

    @Override
     public ExpenseDto deserialize(String topic, Headers headers, byte[] data) {
         return Deserializer.super.deserialize(topic, headers, data);
     }


 }
