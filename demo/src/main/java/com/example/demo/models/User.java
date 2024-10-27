package com.example.demo.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GeneratedColumn;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue()
    private Long id;
    private String name;
    private String lastname;
    private Integer age;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
