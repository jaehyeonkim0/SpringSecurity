package com.springsecurity.dto;

import com.springsecurity.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {

    private String id;
    private String password;
    private String name;

    public User toEntity() {
        return User.builder()
                            .id(id)
                            .password(password)
                            .name(name)
                            .build();
    }
}
