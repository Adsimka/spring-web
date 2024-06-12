package com.example.spring_web.mapper;

import com.example.spring_web.dao.entity.User;
import com.example.spring_web.dto.UserCreateEditDto;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    @Override
    public User map(UserCreateEditDto entity) {
        User user = new User();
        buildUser(entity, user);

        return user;
    }

    public User map(UserCreateEditDto user, User entity) {
        buildUser(user, entity);
        return entity;
    }

    private void buildUser(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setBirthDate(object.getBirthDate());
        user.setRole(object.getRole());
    }
}
