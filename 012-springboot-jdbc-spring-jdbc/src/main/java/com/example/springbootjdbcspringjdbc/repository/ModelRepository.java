package com.example.springbootjdbcspringjdbc.repository;

import com.example.springbootjdbcspringjdbc.model.Model;

import java.util.Map;

public interface ModelRepository {
    // crud

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

    // truncate table / clear table / deleteAll
    boolean deleteAll();
}
