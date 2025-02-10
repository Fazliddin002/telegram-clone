package org.example.telegram.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public record UserDto (

        String username,
        String phone,
        String password,
        String passwordRepeat

){

}
