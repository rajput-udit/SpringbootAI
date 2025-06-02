# Spring AI Huggingface

This project is a Spring Boot application that integrates with Hugging Face AI models. It provides RESTful APIs to interact with Hugging Face services, making it easy to leverage state-of-the-art machine learning models in your Java applications.

## Features
- Spring Boot based REST API
- Integration with Hugging Face models
- Modular structure with controllers, services, and DTOs

## Prerequisites
- Java 17 or higher
- Maven 3.6+

## Getting Started

### Clone the repository
```
git clone <repository-url>
cd spring-ai-huggingface
```

### Configure Hugging Face API
Edit `src/main/resources/application.properties` and add your Hugging Face API key and any other required configuration.

### Build the project
```
./mvnw clean install
```

### Run the application
```
./mvnw spring-boot:run
```
The application will start on [http://localhost:8080](http://localhost:8080).

## Project Structure
- `controller/` - REST controllers for API endpoints
- `service/` - Business logic and Hugging Face integration
- `dto/` - Data transfer objects

## Example Usage
You can use tools like Postman or curl to interact with the API endpoints. Refer to the controller classes for available endpoints.

## License
This project is licensed under the MIT License.
