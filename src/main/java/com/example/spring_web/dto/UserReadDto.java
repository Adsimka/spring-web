package com.example.spring_web.dto;

import com.example.spring_web.dao.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Value
public class UserReadDto {

    @JsonIgnore
    UUID id;

    String username;

    String firstname;

    String lastname;

    LocalDate birthDate;

    Role role;
}
