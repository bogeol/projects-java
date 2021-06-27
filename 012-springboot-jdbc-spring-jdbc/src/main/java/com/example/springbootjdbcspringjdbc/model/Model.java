package com.example.springbootjdbcspringjdbc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Model {
    private int id;
    private String key;
    private String value;
    private String remark;
}
