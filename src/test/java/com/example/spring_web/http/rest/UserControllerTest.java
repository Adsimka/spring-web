package com.example.spring_web.http.rest;

import com.example.spring_web.dao.entity.Role;
import com.example.spring_web.dto.UserCreateEditDto;
import com.example.spring_web.dto.UserReadDto;
import com.example.spring_web.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @SneakyThrows
    void getCountUsers() {
        // We indicate the returned result ourselves
        when(userService.getCountUsers()).thenReturn(2);
        // Expected Result
        mockMvc.perform(get("/api/v1/users/size"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(2));
        // Checking for the number of method calls
        verify(userService, times(1)).getCountUsers();
    }

    @Test
    @SneakyThrows
    void create() {
        UserCreateEditDto user = buildCreateEditUserDto();
        // We describe the User type in JSON
        String userJson = mapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andExpect(status().isCreated());

        verify(userService, times(1)).create(user);
    }

    @Test
    @SneakyThrows
    void findById() {
        UserReadDto user = buildReadUserDto();

        when(userService.findById(100L)).thenReturn(Optional.ofNullable(user));
        mockMvc.perform(get("/api/v1/users/{id}", 100L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.id").value(100L))
                .andExpect(jsonPath("$.username").value("andrey@mail.ru"));
    }

    private UserCreateEditDto buildCreateEditUserDto() {
        return UserCreateEditDto.builder()
                .username("andrey@mail.ru")
                .firstname("Andrey")
                .lastname("Kovalev")
                .birthDate(LocalDate.of(2002, 10, 20))
                .role(Role.USER)
                .build();
    }

    private UserReadDto buildReadUserDto() {
        return UserReadDto.builder()
                .id(100L)
                .username("andrey@mail.ru")
                .firstname("Andrey")
                .lastname("Kovalev")
                .birthDate(LocalDate.of(2002, 10, 20))
                .role(Role.USER)
                .build();
    }
}
