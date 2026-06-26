package org.example.t1academyhome.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "Имя пользователя не должно быть пустым")
    @Size(max = 255, message = "Имя пользователя не должно превышать 255 символов")
    private String username;
}
