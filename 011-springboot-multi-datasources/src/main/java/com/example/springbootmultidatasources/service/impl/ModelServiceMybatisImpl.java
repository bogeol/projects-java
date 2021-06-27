package com.example.springbootmultidatasources.service.impl;

import com.example.springbootmultidatasources.dao.primary.mapper.ModelMapper;
import com.example.springbootmultidatasources.model.primary.Model;
import com.example.springbootmultidatasources.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceMybatisImpl implements ModelService {
    @Autowired
    ModelMapper modelMapper;

    @Override
    public String version() {
        return null;
    }

    @Override
    public void truncateTableModel() {
        modelMapper.truncateTableModel();
    }

    @Override
    public void saveAll(List<Model> modelList) {

    }

    @Override
    public List<Model> findAll() {
        return null;
    }
}
