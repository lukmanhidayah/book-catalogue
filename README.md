# Spring Boot Application

## Overview
This is a Spring Boot application that demonstrates how to build and deploy a Spring Boot project using Docker.

## Prerequisites
- Java 21
- Maven 3.8.4 or later
- Docker

## Running the Application Locally
To run the application locally, use the following command:

```bash
./mvnw spring-boot:run
```

## Building the Application
To build the application, use the following command:

```bash
./mvnw clean package
```

This will generate a JAR file in the [`target`](command:_github.copilot.openRelativePath?%5B%7B%22scheme%22%3A%22file%22%2C%22authority%22%3A%22%22%2C%22path%22%3A%22%2FUsers%2Flukmanhidayah%2FDocuments%2Fproject%2Fownproject%2Fjava%2Fbook-catalog-v2%2Ftarget%22%2C%22query%22%3A%22%22%2C%22fragment%22%3A%22%22%7D%5D "/Users/lukmanhidayah/Documents/project/ownproject/java/book-catalog-v2/target") directory.

## Running the Application with Docker
To build and run the Docker container, follow these steps:

1. Build the Docker image:

    ```bash
    docker build -t spring-boot-app .
    ```

2. Run the Docker container:

    ```bash
    docker run -p 8080:8080 spring-boot-app
    ```

The application will be accessible at `http://localhost:8080`.

## Deploying the Application
To deploy the application, you can push the Docker image to a container registry and then deploy it to your preferred cloud platform. Here is an example using Docker Hub:

1. Tag the Docker image:

    ```bash
    docker tag spring-boot-app your-dockerhub-username/spring-boot-app
    ```

2. Push the Docker image to Docker Hub:

    ```bash
    docker push your-dockerhub-username/spring-boot-app
    ```

3. Deploy the Docker image to your cloud platform (e.g., Kubernetes, AWS ECS, etc.).

## License
This project is licensed under the MIT License - see the LICENSE file for details.
```

Replace `your-dockerhub-username` with your actual Docker Hub username. Adjust the instructions as needed for your specific deployment environment.