package com.example.service.login.impl;

import com.example.service.login.LoginService;
import org.apache.catalina.User;
import org.springframework.data.jpa.domain.Specification;

import java.util.function.Function;

public class LoginServiceImpl implements LoginService {

    public Specification<User> hasAgeBetween18And30() {
        Function<Integer, Integer> square = x -> x * x;
        int result = square.apply(5);
        return (root, query, cb) -> cb.between(root.get("age"), 18, 30);
    }
}
