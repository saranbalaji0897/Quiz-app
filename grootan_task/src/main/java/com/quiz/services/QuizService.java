package com.quiz.services;

import com.quiz.security.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService {

     public Map<String,String> quizAndAnswerMap= new HashMap<String ,String>(){
         {
             put("The longest river in India ?","Ganga");
             put("What is the national fruit of India ?" ,"Mango");
             put("Which is national game of India ?","Hockey");
             put("How many states are there in India?","28");
             put("How many continents are in the world ?","7");
             put("Where is the parliament of India ?","New Delhi");
             put("CharMinar is located in which city ?","Hyderabad");
             put("What is the biggest planet in our solar system?","Jupiter");
             put("The National Tree of India is ?","Banyan");
             put("The first president of India was ?" ,"Dr.Rajendra Prasad");
             put("The highest mountain peak of world ?" ,"Mount Everest");
             put("The National Fruit of India is ?","Mango");
             put("Who is known as the Iron Man Of India ?" ,"Sardar Vallabhai Patel");
             put("The author of famous book Harry Potter is ?","J K Rowling");
             put("Which sport does Sania Mirza play?" ,"Tennis");
             put("Which is the festival of colors","holi");
             put( "What is the filament in a light bulb made of ?","Tungsten");
             put("Who is the current President of India ?","Ram Nath Kovind");
             put("Who is the current Prime Minister of India ?", "Narendra Modi");
             put("Which is the nearest star to Earth ?","Sun");

         }
     };
     public List<String> questionList = new ArrayList<>(quizAndAnswerMap.keySet());

     public List<String> getQuiz(){

             Collections.shuffle(questionList);
             return questionList.subList(0, 5);
     }

     public int  verify(Map<String,String> answers){
         int marks = 0;
             for (Map.Entry<String, String> answer : answers.entrySet()) {
                 if (answer.getValue().toLowerCase().trim().equals(quizAndAnswerMap.get(answer.getKey()).toLowerCase())) {
                     marks++;
                 }
             }
             return marks;

     }




}
