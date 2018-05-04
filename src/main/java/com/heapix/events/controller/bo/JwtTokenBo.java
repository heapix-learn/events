package com.heapix.events.controller.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenBo {

    @NotBlank
    private String tokenType;
    @NotBlank
    private String token;
}
