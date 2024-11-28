package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.requests.StoreUserRequest;
import com.example.demo.requests.UpdateUserRequest;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> index() {
        return userService.index();
    }

    @PostMapping("/with-body")
    public List<User> indexWithBody() {
        return userService.indexWithBody();
    }

    @PostMapping
    public User store(@RequestBody @Valid StoreUserRequest request) {
        return userService.store(request);
    }

    @GetMapping("/{id}")
    public User show(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        userService.destroy(id);
    }
}
