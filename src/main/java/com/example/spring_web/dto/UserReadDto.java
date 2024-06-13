package com.example.spring_web.dto;

import com.example.spring_web.dao.entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserReadDto {

    @JsonIgnore
    Long id;

    String username;

    String firstname;

    String lastname;

    LocalDate birthDate;

    Role role;
}
