# BACKEND TASK - GROOTAN TECHNOLOGIES 

It is a Quiz backend application which is build with java spring-boot framework. 
the user management is handled with jwt and is embedded with swagger-ui
the following lines will describe how to build and run the application

## Building jar 

steps to build a jar

1.  download the project to the local
2.  move the project folder ``` cd /path/to/the/project folder ```
3.  run ``` mvn clean package ```
4.  get the jar from target folder inside the project

## Running jar 

steps to run a jar file

1. to run jar file make sure you have java downloaded in your system else [click here to download](https://www.oracle.com/in/java/technologies/javase-downloads.html)
2. run ``` java -jar /path/to/.jar  ``` in command prompt or terminal
3. to run the jar in required port use cmd``` java -jar /path/to/jar --server.port=port_number ```

## Running Swagger

after running the jar 

1. to run the swagger hit the url``` http://localhost:port/swagger-ui.html ```in your browser
2. create ,verify and login the user by using the authenticationController
3. Get the jwt token as response from login api and paste it in the api key section ``` Bearer <token> ``` which is in the header part of swagger
4. Get quiz and answer it using the QuizController
5. while answering for the quiz use the questions obtained from get api as key to your answer 
 Example
  ```json
  {
    "answerMap":{
        "What is the biggest planet in our solar system?":"Jupiter",
        "The longest river in India ?":"Ganga",
        "Which is the nearest star to Earth ?":"Sun"
    }
  } 
```

## Running jar in docker conatiner
  
  1.use the ```Dockerfile``` inside the project directory to create docker container
  ```
  FROM openjdk:11  //download java to the docker image
  ADD /path/to/projectfoler/target/.jar /path/to/destinationfolder/jar
  EXPOSE 8085  //port to run the docker container
  ENTRYPOINT ["java","-jar","quiz-app.jar","--server.port=8085"]  //command to run jar in required port
  
 ```
 
 2. run command``` sudo docker build -f filename -t container name /path/to/DockerFile/folder ``` to build a container
 3. check for the images using command ```sudo docker images```
 4. run the container using command ```sudo docker run -p <port to the container>:<port to run jar> conatinername```
 
 Example command of above mentioned command syntax
 
 ```
 sudo docker build -f Dockerfile -t quiz-app .
 sudo docker run -p 8085:8085 quiz-app

```


