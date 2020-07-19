package com.quiz.services;


import com.quiz.Models.UserModel;
import com.quiz.Models.VerificationTokenModel;
import com.quiz.dtos.CreateUserDTO;
import com.quiz.dtos.CredentialDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.mail.MailSendException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    MailService mailService;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;




    public Map<String,UserModel> userModelList = new HashMap<>();
    public Map<String, VerificationTokenModel> tokenList = new HashMap<>();
    public static long userId =1;

    public boolean createUser(CreateUserDTO createUserDTO,int port)  {
        if(userModelList.get(createUserDTO.getUsername()) == null) {
//            System.out.println(port);
            String token = UUID.randomUUID().toString();
            String message = "please click the link to verify your account " +
                    "http://localhost:"+port+"/api/auth/verification?token="+token;
            try {
                mailService.sendEmail(createUserDTO.getEmailId(),message);
            }catch (Exception e){
              throw new MailSendException("Invalid email address");
            }
            tokenList.put(token,new VerificationTokenModel(userId,createUserDTO.getUsername(),token));
            userModelList.put(createUserDTO.getUsername(),new UserModel(userId++,createUserDTO.getUsername(),createUserDTO.getEmailId(),bCryptPasswordEncoder.encode(createUserDTO.getPassword()),  false));
            return true;
        }
            return false;

    }



    public UserModel loadUserByUsername(String username){
        UserModel userModel = userModelList.get(username);
        if (userModel != null){
            return userModel;
        }
        return null;
    }

    public boolean updateUserVerification(String token){

        VerificationTokenModel verificationTokenModel = tokenList.get(token);
        if(verificationTokenModel != null){
            UserModel userModel = userModelList.get(verificationTokenModel.getUsername());
            userModelList.put(userModel.getUsername(),new UserModel(userModel.getUserId(),userModel.getUsername(),userModel.getEmailId(),userModel.getPassword(),true));
            return true;
        }

        return false;
    }
}
