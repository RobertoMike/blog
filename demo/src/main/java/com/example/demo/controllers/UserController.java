package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
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

    @PostMapping
    public User store(@RequestBody User user) {
        return userService.store(user);
    }

    @GetMapping("/{id}")
    public User show(@PathVariable Long id) {
        return userService.show(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void destroy(@PathVariable Long id) {
        userService.destroy(id);
    }
}
