FROM openjdk:11
ADD target/quiz-app.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","app.jar","--server.port=8085"]