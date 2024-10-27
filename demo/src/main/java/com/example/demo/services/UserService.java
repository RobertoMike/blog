package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> index() {
        return userRepository.findAll();
    }

    public User store(User user) {
        return userRepository.save(user);
    }

    public User show(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void destroy(Long id) {
        userRepository.deleteById(id);
    }
}
