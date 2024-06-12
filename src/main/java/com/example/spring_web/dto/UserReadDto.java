package com.example.spring_web.dto;

import com.example.spring_web.dao.entity.Role;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Value
public class UserReadDto {

    UUID id;

    String username;

    String firstname;

    String lastname;

    LocalDate birthDate;

    Role role;
}
