package com.example.springbootmybatisreactjs.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String sex;
    private int age;
    private String status;
}
