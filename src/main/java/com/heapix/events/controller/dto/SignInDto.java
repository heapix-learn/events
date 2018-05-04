package com.heapix.events.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInDto {

    @NotBlank
    @Size(max = 255)
    private String email;
    @NotBlank
    @Size(max = 255)
    private String password;
}
