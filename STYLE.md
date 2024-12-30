# Style Guide & Conventions

## Overview
This document outlines the coding conventions and style guide for the Wordle web application project developed by the UW Games team. Following these conventions ensures that the codebase is consistent, maintainable, and readable for all team members.

## Technology Stack

For this project, we will be using the following technology stack:

1. **Database**: 
   - The application will utilize a **MySQL** database to store game-related data such as valid words, user information, and game history.

2. **Backend**: 
   - The backend logic will be handled using **Java** with the **Spring Boot Framework**.
   - The backend will connect to the MySQL database using the **Django ORM** for seamless interaction between the database and the server-side logic.
   - It will expose a **REST API** for the frontend to interact with the backend services.

3. **Frontend**: 
   - The frontend will be built using **JavaScript** and the **React** library, providing a dynamic and responsive user interface.
   - The frontend will communicate with the backend REST API to fetch data, submit user guesses, and display game results.

## Code Conventions

### JavaScript (React)
1. **File Naming**: Use camelCase for file names, e.g., `wordValidator.js`, `gameLogic.js`.
2. **Indentation**: Use 2 spaces for indentation.
3. **Semicolons**: Always use semicolons `;` at the end of statements.
4. **Quotes**: Use single quotes `' '` for strings unless you need to use double quotes for specific HTML attributes, e.g., `const title = 'Wordle Game';`.
5. **Component Naming**: React components should use PascalCase, e.g., `WordleGame.js`, `HeaderComponent.js`.
6. **JSX Syntax**: Use self-closing tags for elements without children, e.g., `<Input />` instead of `<Input></Input>`.
7. **Comments**: Use `//` for single-line comments and `/* ... */` for multi-line comments.

### Java (Spring Boot)
1. **File Structure**: Place each class in a separate file with a filename matching the class name, e.g., `WordController.java`, `GameService.java`.
2. **Class and Method Naming**: 
   - Use PascalCase for class names, e.g., `WordValidator`, `GameController`.
   - Use camelCase for method names, e.g., `validateWord()`, `getGameStatus()`.
3. **Indentation**: Use 4 spaces for indentation.
4. **Braces**: Always use braces `{}` for conditionals and loops, even if there’s only one statement.
5. **Comments**: Use Javadoc style comments for methods and classes, e.g., `/** * This method validates the word * @param word - the word to validate */`, and single-line comments for inline notes.
6. **Annotations**: Use appropriate annotations such as `@Controller`, `@Service`, `@Repository`, e.g., 

   ```java
   @Controller
   public class WordController {
       // Controller logic here
   }

## Gitlab Convention

  - Use the format mentioned in class for branch creation, e.g. yourinitials/task_that_you_are_working_on
 
