package com.example.demo.repositories.specifications;

import com.example.demo.models.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {
    private final String search;
    private final Integer minAge;
    private final Integer maxAge;

    public UserSpecification(String search, Integer minAge, Integer maxAge) {
        this.search = search;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    @Override
    public Predicate toPredicate(@NotNull Root<User> root, CriteriaQuery<?> query, @NotNull CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (search != null) {
            predicates.add(cb.like(root.get("username"), "%" + search + "%"));
            predicates.add(cb.like(root.get("name"), "%" + search + "%"));
            predicates.add(cb.like(root.get("lastname"), "%" + search + "%"));
        }
        if (minAge != null && maxAge != null) {
            predicates.add(cb.between(root.get("age"), minAge, maxAge));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
