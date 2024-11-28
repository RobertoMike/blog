package com.example.demo.models;

import io.github.robertomike.hefesto.models.HibernateModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User implements HibernateModel {
    @Id
    @GeneratedValue()
    private Long id;
    private String username;
    private String name;
    private String lastname;
    private Integer age;

    public User(Long id, String username, String name, String lastname, Integer age) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public User() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
