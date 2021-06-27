package com.example.springbootmultidatasources.service.impl;

import com.example.springbootmultidatasources.dao.primary.jdbc.ModelJdbc;
import com.example.springbootmultidatasources.model.primary.Model;
import com.example.springbootmultidatasources.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ModelServiceJdbcImpl implements ModelService {

    @Autowired
    ModelJdbc modelJdbc;

    @Override
    public String version() {
        return modelJdbc.selectVersion();
    }

    @Override
    public void truncateTableModel() {
    }

    @Override
    public void saveAll(List<Model> modelList) {
    }

    @Override
    public List<Model> findAll() {
        return null;
    }
}
