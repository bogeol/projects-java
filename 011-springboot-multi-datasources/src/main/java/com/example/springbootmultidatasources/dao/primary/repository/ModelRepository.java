package com.example.springbootmultidatasources.dao.primary.repository;

import com.example.springbootmultidatasources.model.primary.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

}
