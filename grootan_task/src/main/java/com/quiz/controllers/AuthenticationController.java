package com.quiz.controllers;

import com.quiz.Models.UserModel;
import com.quiz.dtos.CreateUserDTO;
import com.quiz.dtos.CredentialDTO;
import com.quiz.helpers.R;
import com.quiz.security.User;
import com.quiz.security.jwtutils.JwtTokenUtils;
import com.quiz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtils jwtTokenUtils;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public  R<?> registerUser(@Valid @RequestBody CreateUserDTO createUserDTO, HttpServletRequest httpRequest){
        if(userService.createUser(createUserDTO,httpRequest.getServerPort())){
            return new R<>("verification email is sent ",HttpStatus.OK);
        }
        return new R<>("username already exists", HttpStatus.CONFLICT);
    }

    @PostMapping("/login")
    public  R<?> loginUser(@Valid @RequestBody CredentialDTO createUserDTO){
        UserModel user = userService.loadUserByUsername(createUserDTO.getUsername());
        if(user != null){
            if(user.isVerified()){
                if(bCryptPasswordEncoder.matches(createUserDTO.getPassword(),user.getPassword())){
                    String jwtToken = jwtTokenUtils.generateToken(new User(user.getUserId(),user.getUsername()));
                    return new R<>(jwtToken,HttpStatus.OK);
                }else{
                    return new R<>("password doesnot match",HttpStatus.FORBIDDEN);
                }
            }else{
                return new R<>("unverified user",HttpStatus.FORBIDDEN);
            }
        }
        return new R<>("username does not exist", HttpStatus.OK);
    }

    @GetMapping("/verification")
    public String verifyEmail(@NotNull @RequestParam("token") String token){
       if(userService.updateUserVerification(token)){
           return "verification success";
       }
       return "invalid token";
    }
}
