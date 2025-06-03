# Spring AI Studio Custom Response

This project demonstrates a Spring Boot application that integrates with AI models (such as Gemini) to provide custom responses via RESTful endpoints. It is designed to showcase how to interact with AI chat models and process their responses into structured Java objects.

## Features
- REST API endpoints to query AI models for:
  - Lists of popular sports personalities
  - Player achievements and details
- Uses Spring AI libraries for prompt creation and response conversion
- Example of using output converters for lists and beans

## Project Structure
- `controller/GeminiModelController.java`: Main REST controller exposing endpoints for AI queries
- `model/Player.java`: Model representing a sports player
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
- `GET /sports/{sports}`
  - Returns a list of 5 most popular personalities in the given sport.
  - Example: `/sports/cricket`
- `GET /?sports={sports}`
  - Returns a `Player` object with achievements for the given sportsperson.
  - Example: `/?sports=cricket`

## Customization
- Modify prompts in `GeminiModelController.java` to change the AI queries or output format.
- Extend the `Player` model as needed for additional fields.

## License
This project is for educational/demo purposes.
