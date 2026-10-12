package org.example.app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table()
public class UserRole {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="role_id")// mapping with database name like in database its snake case and in Jva entities its camel case
    private Long  roleId;
    private String   name;






}
