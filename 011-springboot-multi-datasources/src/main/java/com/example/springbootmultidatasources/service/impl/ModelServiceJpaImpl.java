package com.example.springbootmultidatasources.service.impl;

import com.example.springbootmultidatasources.dao.primary.repository.ModelRepository;
import com.example.springbootmultidatasources.model.primary.Model;
import com.example.springbootmultidatasources.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceJpaImpl implements ModelService {
    @Autowired
    ModelRepository modelRepository;

    @Override
    public String version() {
        return null;
    }

    @Override
    public void truncateTableModel() {

    }

    @Override
    public void saveAll(List<Model> modelList) {
        modelRepository.saveAll(modelList);
    }

    @Override
    public List<Model> findAll() {
        return modelRepository.findAll();
    }
}
