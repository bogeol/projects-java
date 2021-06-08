package org.example.model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private UUID uuid;
    private String username;
    private String password;
    private String sex;
    private double hit;
    private boolean status;
}
