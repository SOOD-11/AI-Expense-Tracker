package com.example.expenseService.service;


import com.example.expenseService.Dto.ExpenseDto;
import com.example.expenseService.Entities.Expense;
import com.example.expenseService.repository.ExpenseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseService {

    // the service needs Repository
@Autowired
private   ExpenseRepository expenseRepo;

 ObjectMapper obj=new ObjectMapper();


public  boolean  createExepense(ExpenseDto exp){
setCurrency(exp);
     try {
         expenseRepo.save(obj.convertValue(exp,Expense.class));
         return true;
     } catch (IllegalArgumentException e) {
         e.printStackTrace();
     }
     return false;
 }
// what will be the flow of update
    // first of all fetch the expense needs to update
    // then save the current changes tot he dto and then using objectmapper save it in the repo
public  void updateExpense(ExpenseDto dto){
    Optional<Expense> expense= expenseRepo.findByUserIdAndExternalId(dto.getUserId(),dto.getExternalId());


    if(expense.isPresent()){
        // need to some actions in db  right so we need expense entity not dTo
 Expense currentExpense=expense.get();
 currentExpense.setMerchant(Strings.isNotBlank(dto.getMerchant()) ? dto.getMerchant() : currentExpense.getMerchant() );
currentExpense.setAmount(Strings.isNotBlank(dto.getAmount())? dto.getAmount(): currentExpense.getAmount());
expenseRepo.save(currentExpense);
    }
}
public   List<ExpenseDto>   getExpenses(String UserId){

    List<Expense> exp=expenseRepo.findByUserId(UserId);
     return obj.convertValue(exp, new TypeReference<List<ExpenseDto>>() {}) ;


}
public  List<ExpenseDto> getExpensesfromDate(String userId, Timestamp date){

    List<Expense> exp=expenseRepo.findByUserIdAndCreatedAt(userId,date);
     return obj.convertValue(exp, new TypeReference <List<ExpenseDto>>() {});
}

public void setCurrency(ExpenseDto dto){
// if u didint get from kafka  like some the dto so u have to set default value
     if(Objects.isNull(dto.getCurrency())){
dto.setCurrency("INR");
     }

 }






}
