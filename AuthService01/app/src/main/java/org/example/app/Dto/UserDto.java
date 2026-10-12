package org.example.app.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.example.app.entities.User;


@Data



@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserDto extends User {


    @JsonProperty("phoneNumber")
    private Long phoneNumber;
    private String email;


}
