package com.example.expenseService.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {

private String externalId;
@JsonProperty(value="amount")
private String amount;
    @JsonProperty(value="user_id")
private String userId;
    @JsonProperty(value="merchant")
private String merchant;
    @JsonProperty(value="currency")
private String currency;
    @JsonProperty(value="created_At")
    private Timestamp createdAt;

}
