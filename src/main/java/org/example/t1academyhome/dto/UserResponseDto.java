package org.example.t1academyhome.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.t1academyhome.entity.User;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
