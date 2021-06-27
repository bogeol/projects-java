package com.example.springbootjdbcspringjdbc.service.impl;

import com.example.springbootjdbcspringjdbc.model.Model;
import com.example.springbootjdbcspringjdbc.repository.ModelRepository;
import com.example.springbootjdbcspringjdbc.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("modelServiceImpl")
public class ModelServiceImpl implements ModelService {
    @Autowired
    @Qualifier("modelRepositoryImpl")
    ModelRepository modelRepository;


    @Override
    public Map<String, Object> save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public Model getById(int id) {
        return modelRepository.getById(id);
    }

    @Override
    public boolean updateById(int id, Model model) {
        return modelRepository.updateById(id, model);
    }

    @Override
    public boolean deleteById(int id) {
        return modelRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> getAll() {
        return modelRepository.getAll();
    }

    @Override
    public boolean deleteAll() {
        return modelRepository.deleteAll();
    }
}
