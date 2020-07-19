package com.quiz.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationTokenModel {

    private long userId;

    private String username;

    private String userToken;

}
