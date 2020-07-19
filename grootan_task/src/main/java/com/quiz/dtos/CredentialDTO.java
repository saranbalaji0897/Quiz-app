package com.quiz.dtos;



import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CredentialDTO {

    @NotBlank
    @Size(max=15)
    public String username;

    @NotBlank
    @Size(max=15)
    public String password;

}
