package com.springsecurity.dto;

import com.springsecurity.model.Role;
import com.springsecurity.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String id;

    private String password;

    private String name;

    private Set<Long> roles;

    public User toEntity(Set<Role> roles) {
        return User.builder().id(id).password(password).name(name).roles(roles).build();
    }
}
