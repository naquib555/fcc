FROM eclipse-temurin:17-jdk-alpine AS build

COPY . /app
WORKDIR /app
RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM eclipse-temurin:17-jre-alpine

COPY --from=build /app/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]