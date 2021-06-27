package com.example.springbootmultidatasources.service;

import com.example.springbootmultidatasources.model.primary.Model;

import java.util.List;

public interface ModelService {
    String version();

    void truncateTableModel();

    void saveAll(List<Model> modelList);

    List<Model> findAll();
}
