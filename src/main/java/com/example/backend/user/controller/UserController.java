package com.example.backend.user.controller;

import com.example.backend.exception.exceptions.EntityNotFoundException;
import com.example.backend.user.dto.UserDto;
import com.example.backend.user.model.User;
import com.example.backend.user.serializer.UserSerializer;
import com.example.backend.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto get(@RequestParam(value = "email") String email) throws EntityNotFoundException {
        User user = userService.get(email);
        return UserSerializer.serialize(user, UserSerializer.Task.BASE, UserSerializer.Task.FILTERS);
    }

}
