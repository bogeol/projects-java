package com.example.springbootmultidatasources.dao.primary.mapper;

import com.example.springbootmultidatasources.model.primary.Model;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScans;

import java.util.List;

@Mapper
public interface ModelMapper {
    List<Model> findAll();

    Model findById(Long id);

    void truncateTableModel();
}
