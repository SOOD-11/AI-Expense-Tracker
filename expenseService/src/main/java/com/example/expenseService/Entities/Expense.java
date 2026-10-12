package com.example.expenseService.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Expense {
@Id
@Column(name="id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@Column(name="external_id")
public String externalId;
    @Column( name="merchant")
    private String merchant;
    @Column( name="user_id")
    private String userId;
    @Column( name="amount")
    private String amount;
@Column(name="created_at")
    private Date createdAt;

    @PrePersist
    @PreUpdate
    private void generateExternalId(){
if(this.externalId==null){
    this.externalId= UUID.randomUUID().toString();


}

    }
}
