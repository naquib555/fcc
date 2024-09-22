# Receipt Processor

This is a Spring Boot application that exposes APIs for processing receipts. The service can be accessed via Swagger UI at
- [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Prerequisites

Before you can run this application, you will need:

- Java Development Kit (JDK) 21 or above installed on your machine.
- Gradle 8.10 (if gradle wrapper is not used)
- Docker

You can check your current versions by running `java -version` and `gradle -v` in the command line.

## Building the Project

To build the project, follow these steps:

1. Open a terminal in the root directory of the project (where the `build.gradle.kts` file resides)
2. Run `./gradlew clean build -x test` to build the project using Gradle. The built JAR file will be placed in the `build/libs` directory.
3. If Gradle wrapper `./gradlew` is not used then `gradle clean build -x test` can be used (Gradle 8.10 must be installed)

## Running the Application locally

To run the application locally, use the `java -jar` command followed by the path to the JAR file:

```shell
java -jar build/libs/receipt-processor-1.0.0.jar
```

The application will then be accessible at [http://localhost:8080](http://localhost:8080).

## Building and Running the Docker Image

You can also build a Docker image and run the application inside a Docker container.

### Building the Docker Image

To build the Docker image, run this command in the root directory of the project (where the Dockerfile resides):

```shell
docker build -t receipt-processor .
```

This will create a Docker image named `receipt-processor`.

### Running the Docker Container

To run the application inside a Docker container, use the `docker run` command:

```shell
docker run -p 8080:8080 receipt-processor
```

The application will then be accessible at [http://localhost:8080](http://localhost:8080), and the Swagger UI will be available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).