
# Foro hub API



## Badges



[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)

## Index

* [Title](#title)
* [Badges](#badges)
* [Index](#index)
* [Project Description](#project-description)
* [Application Features and Demo](#application-features-and-demo)
* [API Reference](#API-Reference)
* [Project Access](#project-access)
* [Technologies Used](#technologies-used)
* [License](#license)
* [Conclusion](#conclusion)
## Description


Foro hub is a forum platform designed to encourage interaction and knowledge exchange among users. This project focuses exclusively on the 
development of the API and backend, providing a robust infrastructure for user management and the creation and handling of topics.

## Features

- User Registration
    - New users can register by providing the necessary information to create an account.
- Authentication
    - Registered users must log in to obtain a valid token. This token is required to access the protected endpoints of the API.
- Topic Management
    - Create Topics: Authenticated users can create new discussion topics.
    - Respond to Topics: Users can respond to existing topics.
    - Mark Topics as Resolved: Topics can be marked as resolved, indicating that the discussion has reached a satisfactory conclusion.

This API serves as the backbone for the foro_hub platform, ensuring secure and efficient management of user interactions and topic discussions.
## API Reference

#### Get all topic

```http
  GET /topic
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `api_key` | `string` | **Required**. Your API key |

#### Post User

```http
  POST /user
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `api_key` | `string` | **Required**. Id of item to fetch |

#### add(num1, num2)

Takes two numbers and returns the sum.


## Project Access

1. Clone the Repository

```bash
  https://github.com/jesusdavid24/challenge-foro-hub.git
```
2. Navigate to the project directory

```bash
  cd foro_hub
```
3. Use Maven to build the project and download the necessary dependencies

```bash
  mvn clean install
```
4. Configure the Application:

```bash
Modify the application.properties file in the src/main/resources directory to suit your 
local environment. This includes database configurations and other necessary settings
```

```bash
    spring.datasource.url=jdbc:mysql://${DATASOURCE_HOST}/${DATASOURCE_NAME}
    spring.datasource.username=${DATASOURCE_USERNAME}
    spring.datasource.password=${DATASOURCE_PASSWORD}

    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.main.allow-bean-definition-overriding=true
    server.error.include-stacktrace=never

    api.security.secret=${JWT_SECRET:123456}
```
5. Run the Application

```bash
  mvn spring-boot:run
```
6. Access the API

```bash
Once the application is running, you can access the API at http://localhost:8080.
Use an API client like Postman to interact with the endpoints for user registration, 
authentication, topic creation, and responses.
```
7. API Documentation and Testing Interface

```bash
Springdoc and Swagger: The project uses Springdoc and Swagger for API documentation. 
You can access the Swagger UI to explore and test all available endpoints.

Open your browser and go to http://localhost:8080/swagger-ui.html to view the Swagger 
interface.

This interface allows you to test all the endpoints directly from your browser, 
making it easy to interact with the API and understand its functionality.
```
## Tech Stack

1. Java 17
2. Spring Boot 3
3. Maven
4. Spring Security
5. JWT (JSON Web Tokens)
5. Spring Data JPA
6. H2 Database
7. BD MySQL
8. Springdoc and Swagger



## Authors

- [@jesusdavid24](https://github.com/jesusdavid24)


## License

[MIT](https://choosealicense.com/licenses/mit/)

## Conclusion

Foro_Hub represents a robust forum platform backend built with Java, Spring Boot 3, and Maven. It facilitates seamless user registration, 
authentication using JWT tokens, and comprehensive topic management functionalities. Leveraging technologies like Spring Security, Spring Data JPA, 
and H2 Database, the project ensures secure and efficient data handling. Additionally, integrating Springdoc with Swagger offers an interactive 
API documentation interface, enhancing developer usability and understanding of endpoints. With these features, Foro_Hub provides a solid foundation 
for building a scalable and user-friendly forum application.