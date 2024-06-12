package com.example.spring_web.mapper;

import com.example.spring_web.dao.entity.User;
import com.example.spring_web.dto.UserReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User entity) {
        return UserReadDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .birthDate(entity.getBirthDate())
                .role(entity.getRole())
                .build();
    }
}
