package com.example.expenseService.RestController;

import com.example.expenseService.Dto.ExpenseDto;
import com.example.expenseService.service.ExpenseService;
import jakarta.websocket.server.PathParam;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    ExpenseController(ExpenseService expenseService) {

        this.expenseService = expenseService;

    }


    @GetMapping("/v1/get-expense")
    public ResponseEntity<List<ExpenseDto>> getExpenses(@PathParam("user_id") @NonNull String userId) {

        List<ExpenseDto> expenses = null;
        try {
            expenses = expenseService.getExpenses(userId);
            return new ResponseEntity<>(expenses, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);


        }
        @PostMapping("v1/create-expense")
                public ResponseEntity<String>  addExpense()
    }
}




