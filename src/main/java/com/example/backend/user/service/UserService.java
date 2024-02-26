package com.example.backend.user.service;

import com.example.backend.common.ErrorCode;
import com.example.backend.exception.exceptions.EntityNotFoundException;
import com.example.backend.user.model.User;
import com.example.backend.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User get(String email) throws EntityNotFoundException {
        return userRepository.findByEmailWithFilters(email).orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
