FROM openjdk:8
WORKDIR /app
COPY immfly-codingChallenge /app
RUN ./mvnw package -DskipTests
EXPOSE 8080
CMD ["java","-jar","./target/immfly-codingChallenge-0.0.1-SNAPSHOT.jar"]