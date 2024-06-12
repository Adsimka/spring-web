package com.example.spring_web.http.rest;

import com.example.spring_web.dto.UserReadDto;
import com.example.spring_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }
}
