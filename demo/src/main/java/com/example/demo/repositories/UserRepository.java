package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.repositories.specifications.UserSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    List<User> findByUsernameAndNameAndLastnameAndAgeBetween(
            String username, String name, String lastname, Integer minAge, Integer maxAge
    );
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);
    List<User> findByUsernameAndNameAndLastname(String username, String name, String lastname);
}
