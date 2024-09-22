FROM eclipse-temurin:21-jre
WORKDIR /app
COPY build/libs/receipt-processor-1.0.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]