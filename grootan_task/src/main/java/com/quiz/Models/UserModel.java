package com.quiz.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {

    private long userId;

    private String username;

    private String emailId;

    private String password;

    private boolean isVerified;
}
