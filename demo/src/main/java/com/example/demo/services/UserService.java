package com.example.demo.services;

import com.example.demo.exceptions.NotFoundException;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.specifications.UserSpecification;
import com.example.demo.requests.StoreUserRequest;
import com.example.demo.requests.UpdateUserRequest;
import io.github.robertomike.baradum.Baradum;
import io.github.robertomike.baradum.filters.ExactFilter;
import io.github.robertomike.baradum.filters.IntervalFilter;
import io.github.robertomike.baradum.filters.SearchFilter;
import io.github.robertomike.hefesto.enums.Operator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> indexWithoutNothing(
            String search, Integer minAge, Integer maxAge
    ) {
        if (search != null && minAge != null && maxAge != null) {
            return userRepository.findByUsernameAndNameAndLastnameAndAgeBetween(
                    search, search, search, minAge, maxAge
            );
        }
        if (search == null && minAge != null && maxAge != null) {
            return userRepository.findByAgeBetween(minAge, maxAge);
        }
        if (search != null && minAge == null && maxAge == null) {
            return userRepository.findByUsernameAndNameAndLastname(search, search, search);
        }
        return userRepository.findAll();
    }

    public List<User> indexWithSpecification(
            String search, Integer minAge, Integer maxAge
    ) {
        return userRepository.findAll(new UserSpecification(search, minAge, maxAge));
    }

    public List<User> index() {
        return Baradum.make(User.class)
                .allowedFilters(
                        SearchFilter.of("username", "name", "lastname"), // Will use search as key for the query
                        new IntervalFilter("age")
                )
                // Allows you to order by any of the fields declared here
                .allowedSort("username", "name", "lastname", "age")
                .get();
    }

    public List<User> indexWithMoreThings() {
        return Baradum.make(User.class)
                .allowedFilters(
                        SearchFilter.of("username", "name", "lastname"), // Will use search as key for the query
                        new IntervalFilter("age"),
                        // Filter relationship values and create an alias
                        new ExactFilter("country", "addresses.country")
                )
                // Allows you to order by any of the fields declared here
                .allowedSort("username", "name", "lastname", "age")
                // Fixed condition
                .where("age", Operator.GREATER_OR_EQUAL, 10)
                // Accessing the current instance of Hefesto to perform advanced operations
                // Putting a fixed order
                .builder(builder -> builder.orderBy("username"))
                .get();
    }

    public List<User> indexWithBody() {
        return Baradum.make(User.class)
                // You can pass only the name of fields, and will become ExactFilters
                .allowedFilters("username", "name", "lastname", "age")
                // Allows you to order by any of the fields declared here
                .allowedSort("username", "name", "lastname", "age")
                .useBody() // Allows to receive JSON from post request
                // If you only want to support body operations, use onlyBody()
                .get();
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
