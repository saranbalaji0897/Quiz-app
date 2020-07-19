package com.quiz.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.SendFailedException;

@Slf4j
@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String emailId,String message)  {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);
        msg.setSubject("Verification Email");
        msg.setText(message);
        javaMailSender.send(msg);
//        try {
//            javaMailSender.send(msg);
//        }
//        catch (MailSendException ex) {
//            throw new MailSendException("invalid mail address");
//        }
    }
}
