package com.example.springbootmultidatasources.dao.secondary.repository;

import com.example.springbootmultidatasources.model.secondary.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
