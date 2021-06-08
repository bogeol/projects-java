package com.example.springbootspringdatajpa.service;

import com.example.springbootspringdatajpa.model.User;
import com.example.springbootspringdatajpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveAll(List<User> userList) {
        userRepository.saveAll(userList);
    }

    public User findUserByUserId(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            return null;
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> findAllByPagingAndSorting(int pageNumber, int pageSize) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "id"))).getContent();
    }
}
