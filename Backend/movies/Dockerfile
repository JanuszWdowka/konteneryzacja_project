FROM openjdk:17
EXPOSE 8080:8080

ADD target/movies.jar movies.jar

ENTRYPOINT ["java", "-jar", "movies.jar"]