package com.quiz.controllers;


import com.quiz.dtos.AnswerDTO;
import com.quiz.helpers.R;
import com.quiz.security.User;
import com.quiz.services.QuizService;
import com.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    @Autowired
    QuizService quizService;

    @Autowired
    UserService userService;

    @GetMapping
    public R<?> getQuiz(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userService.userModelList.get(user.getUsername()) != null) {
            return new R<>(quizService.getQuiz(), HttpStatus.OK);
        }
        return new R<>("user not found",HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public R<?> verifyQuiz(@Valid  @RequestBody AnswerDTO answerDTO){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userService.userModelList.get(user.getUsername()) != null) {
            int mark = quizService.verify(answerDTO.getAnswerMap());
            return new R<>("your score is "+mark,HttpStatus.OK);
        }
        return new R<>("user not found",HttpStatus.UNAUTHORIZED);
    }
}
