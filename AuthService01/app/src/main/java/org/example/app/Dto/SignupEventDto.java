package org.example.app.Dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@Builder

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SignupEventDto {


    private String userId;
    private String email;
    private String username;
    private Long phoneNumber;
    private String role;
}
