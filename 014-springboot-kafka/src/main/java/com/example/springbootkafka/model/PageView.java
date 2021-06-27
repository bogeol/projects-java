package com.example.springbootkafka.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageView {
    Long id;
    String firstName;
    String lastName;
    String email;
    String nothing;
    String ipAddress;
    Date viewDate;
}

