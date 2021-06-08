package com.example.springbootspringdatajpa.model;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private int age;
    private String status;
}
