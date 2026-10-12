package com.example.userService.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonTypeId;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor// when all member need to be initialized
@NoArgsConstructor// when no member needed to be initialized
@Builder //  when not all but some members need to be initialized
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)// doing becasue from kafka producer the deserialization will co0me in snake case instead of cmael case
@JsonIgnoreProperties(ignoreUnknown = true)// well it for if after deserializer the kafka event payload something comes null it would be ignored
@Data
public class UserInfoDto {

// increment count
    Long id;
    @Id // making it as primary key
    private String userId;

    private String username;
    private String email;
    private Long phoneNumber;

}
