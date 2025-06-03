# Spring AI Studio User Prompts

This project is a Spring Boot application that demonstrates how to interact with AI models (such as Gemini) using custom user prompts via RESTful endpoints. It showcases how to send dynamic prompts to an AI model and return the generated responses to the client.

## Features
- REST API endpoints to query AI models for:
  - Lists of popular sports personalities
  - Career achievements of sports personalities
  - Custom user messages
- Uses Spring AI libraries for prompt creation and response handling
- Example of using system and user messages for context-aware AI responses

## Project Structure
- `controller/GeminiModelController.java`: Main REST controller exposing endpoints for AI queries
- `model/`: Contains model classes for AI responses
- `resources/application.properties`: Configuration for API keys and base URLs

## Prerequisites
- Java 17 or higher
- Maven 3.6+
- API key for the AI provider (e.g., Gemini/OpenAI)

## Setup & Running
1. **Clone the repository**
2. **Configure API Keys**
   - Edit `src/main/resources/application.properties` and set:
     - `spring.ai.openai.api-key=YOUR_API_KEY`
     - `spring.ai.openai.chat.base-url=YOUR_BASE_URL`
3. **Build the project**
   ```sh
   mvn clean install
   ```
4. **Run the application**
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
- `GET /{message}`
  - Returns the AI-generated response for a custom message.
  - Example: `/hello world`
- `GET /sports/{sports}`
  - Returns a list of 5 most popular personalities in the given sport with their achievements.
  - Example: `/sports/cricket`
- `GET /?sports={sports}`
  - Returns a list of 5 most popular personalities in the given sport with their achievements, using a system message for context.
  - Example: `/?sports=cricket`

## Customization
- Modify prompts in `GeminiModelController.java` to change the AI queries or output format.
- Extend the model classes as needed for additional fields or response types.

## License
This project is for educational/demo purposes.
