package com.example.springbootmultidatasources.service.impl;

import com.example.springbootmultidatasources.dao.primary.jdbctemplate.ModelJdbcTemplate;
import com.example.springbootmultidatasources.model.primary.Model;
import com.example.springbootmultidatasources.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceJdbcTemplateImpl implements ModelService {
    @Autowired
    ModelJdbcTemplate modelJdbcTemplate;

    @Override
    public String version() {
        return modelJdbcTemplate.selectVersion();
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
