package org.example.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="users")
@Entity
@SuperBuilder
public class User {


    @Id
    @Column(name="user_id")
private String userId;
    @Column(name="user_name")
 private String username;

 private String password;
private Long phoneNumber;
private String email;

 @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(

       name="user_roles",

            joinColumns=@JoinColumn(name="user_id"),

            inverseJoinColumns=@JoinColumn(name="role_id")

    )

    private Set<UserRole> roles= new HashSet<>();

}
