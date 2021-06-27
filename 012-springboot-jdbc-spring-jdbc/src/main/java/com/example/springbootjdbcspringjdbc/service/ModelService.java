package com.example.springbootjdbcspringjdbc.service;

import com.example.springbootjdbcspringjdbc.model.Model;

import java.util.Map;

public interface ModelService {

    // 1. create
    Map<String, Object> save(Model model);

    // 2. retrieve
    Model getById(int id);

    // 3. update
    boolean updateById(int id, Model model);

    // 4. delete
    boolean deleteById(int id);

    // other.
    Map<String, Object> getAll();

    boolean deleteAll();
}
