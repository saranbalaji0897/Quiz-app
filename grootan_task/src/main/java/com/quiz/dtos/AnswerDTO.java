package com.quiz.dtos;



import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.HashMap;


@Data
public class AnswerDTO {

    @NotNull
    private HashMap<String,String> answerMap;

}
