package com.example.spring_web.service;

import com.example.spring_web.dao.UserRepository;
import com.example.spring_web.dto.UserCreateEditDto;
import com.example.spring_web.dto.UserReadDto;
import com.example.spring_web.mapper.UserCreateEditMapper;
import com.example.spring_web.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserReadMapper readMapper;
    private final UserCreateEditMapper editMapper;

    private final UserRepository userRepository;

    public Optional<UserReadDto> findById(UUID id) {
        return userRepository.findById(id)
                .map(readMapper::map);
    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(readMapper::map)
                .toList();
    }

    @Transactional
    public boolean delete(UUID id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    @Transactional
    public UserReadDto create(UserCreateEditDto user) {
        return Optional.of(user)
                .map(dto -> {
                    return editMapper.map(dto);
                })
                .map(userRepository::saveAndFlush)
                .map(readMapper::map)
                .orElseThrow();
    }
}
