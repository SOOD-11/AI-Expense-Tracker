package com.example.expenseService.repository;

import com.example.expenseService.Entities.Expense;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense,Long> {

    List<Expense> findByUserId(String userId);

    List<Expense>  findByUserIdAndCreatedAt(String userId, Timestamp createdAt);
    Optional<Expense> findByMerchant(String merchant);
    Optional<Expense> findByUserIdAndExternalId(String userid,String externalId);

}
