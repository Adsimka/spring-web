package com.example.spring_web.http.rest;

import com.example.spring_web.dto.UserReadDto;
import com.example.spring_web.service.UserService;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.json.MappingJacksonValue;
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

    @GetMapping("/filtering")
    public MappingJacksonValue findAllByFilter() {
        List<UserReadDto> users = userService.findAll();
        return getMappingJacksonValue(users, "firstname", "lastname", "birthDate");
    }

    private MappingJacksonValue getMappingJacksonValue(List<UserReadDto> users, String... fields) {
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filters = new SimpleFilterProvider().addFilter("UsersFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }
}
