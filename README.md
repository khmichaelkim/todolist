## ToDoList App


### Description
The ToDoList application is a serverless, scalable task management system that allows users to manage their tasks efficiently. 
It provides a set of APIs to create, retrieve, update, and delete tasks and user information, focusing on ease of use and flexibility.

### Tech Stack

__Java 11__: Core programming language for backend development. \
__AWS Lambda__: Serverless compute service to run the backend code. \
__Amazon API Gateway__: Service for creating, publishing, and securing APIs. \
__Amazon DynamoDB__: NoSQL database service for fast and predictable performance. \
__Gradle__: Build automation tool for managing dependencies and packaging. \
__JUnit__: Testing framework for Java applications. \
__SLF4J with Logback__: Logging framework for logging status and error messages.

### How To Use

#### Setting Up The Backend
1. Clone the repo and package the project
2. Deploy AWS Lambda functions for each operation defined in the project (e.g., CreateUser)
3. Configure API Gateway to create endpoints for Lambda functions. Endpoints are outlined in `/docs/api/openapi-spec.yaml`
4. Configure DynamoDB tables per the design outlined in `/docs/usersTable.yaml` and `/docs/tasksTable.yaml`

#### Interacting with the API
* To create tasks or users, send a POST request to /users or /users/{userId}/tasks. 
* To retrieve, update, or delete tasks, use the GET, PUT, and DELETE methods on /users/{userId}/tasks/{taskId}.

#### Accessing the Deployed Application
A lightweight deployed frontend application can be found here: [ToDoList App](https://task-management-api-khmichaelkim.replit.app/) \
__Note__: Initial operation may take 5-15 seconds due to Lambda functions' cold starts. For demonstration purposes only. 



_Additional details can be found in the [Design Document](docs/design_document.md)_