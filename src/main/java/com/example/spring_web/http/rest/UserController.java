package com.example.spring_web.http.rest;

import com.example.spring_web.dto.UserCreateEditDto;
import com.example.spring_web.dto.UserReadDto;
import com.example.spring_web.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreateEditDto user) {
        UserReadDto userEdit = userService.create(user);
        return ResponseEntity.ok(userEdit);
    }

    @GetMapping
    public ResponseEntity<List<UserReadDto>> findAll() {
        List<UserReadDto> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Long id, UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

    @GetMapping("/size")
    public ResponseEntity<Integer> getCountUsers() {
        int size = userService.getCountUsers();
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

}
