package com.quiz.dtos;


import lombok.Data;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserDTO {


    @NotBlank
    @Size(max = 30)
    private String username;

    @Email
    private String emailId;

    @NotBlank
    @Size(max=15)
    private String password;



}
