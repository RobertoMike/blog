package com.example.demo.services;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.requests.StoreUserRequest;
import com.example.demo.requests.UpdateUserRequest;
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

    public User store(StoreUserRequest request) {
        var user = transformRequest(request, new User());
        user.setUsername(request.getUsername());

        return userRepository.save(user);
    }

    private User transformRequest(UpdateUserRequest request, User user) {
        user.setName(request.getName());
        user.setLastname(request.getLastname());
        user.setAge(request.getAge());
        return user;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User update(Long id, UpdateUserRequest request) {
        var user = findById(id);

        return userRepository.save(transformRequest(request, user));
    }

    public void destroy(Long id) {
        userRepository.deleteById(id);
    }
}
