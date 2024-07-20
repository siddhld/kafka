# Learning Navigator - Exam Enrollment API

This project implements a RESTful API service using Spring Boot for managing the exam enrollment process in a Learning Management System (LMS). It uses MySQL for persistent data storage.

## Features

- CRUD operations for Students, Subjects, and Exams.
- Foreign key relationships between entities.
- Validation for student enrollment in a subject before exam registration.
- Global error handling with appropriate HTTP status codes.
- Basic unit tests using MockMvc and Mockito.

## Technologies

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)
![Mockito](https://img.shields.io/badge/Mockito-25A162?style=for-the-badge&logo=mockito&logoColor=white)


## Unit Tests

- Unit tests for core functionalities are included under the `src/test/java` directory.

## Getting Started

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/learning-navigator.git
   cd learning-navigator
   ```
2. **Install the required dependencies:**
   - For Maven:
     ```bash
     mvn install
     ```
   - For Gradle:
     ```bash
     ./gradlew build
     ```
3. **Configure MySQL connection details:**
   Update the `src/main/resources/application.properties` file with your MySQL connection details:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/yourdbname
   spring.datasource.username=yourusername
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   ```
4. **Run the application:**
   - For Maven:
     ```bash
     mvn spring-boot:run
     ```
   - For Gradle:
     ```bash
     ./gradlew bootRun
     ```

5. **Test the API using Postman:**
   You can import the Postman collection by clicking the button below:

   [<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/30359334-740dca64-7c0c-4e16-bba2-ffc6c3bb4abc?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D30359334-740dca64-7c0c-4e16-bba2-ffc6c3bb4abc%26entityType%3Dcollection%26workspaceId%3D5ce135f8-2a72-4136-886b-2b6dff060ef7)

Feel free to fork this project and contribute by adding new features or improving existing functionalities.
