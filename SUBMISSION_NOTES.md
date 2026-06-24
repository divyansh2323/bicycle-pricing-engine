# Bicycle Pricing Engine – Submission Notes

## 1. Questions Considered During Solution Design

While designing the solution, the following questions were considered:

1. How should bicycle parts be represented and stored in the system?
2. How should a bicycle configuration be modeled so that it can contain multiple parts?
3. How can the application calculate configuration pricing dynamically using current part prices?
4. Should historical price changes be tracked separately from current pricing information?
5. How should the frontend and backend communicate in a clean and maintainable manner?
6. How should the API be structured to support future enhancements?
7. How should exceptions and invalid requests be handled?
8. How can the solution remain modular and scalable as additional part categories are introduced?
9. How should data transfer between frontend and backend be managed?
10. What architecture would best support separation of concerns and maintainability?

---

## 2. Assumptions Made

1. Each bicycle part belongs to a single category.
2. Each part has a unique identifier.
3. A bicycle configuration consists of one or more bicycle parts.
4. A part can be reused across multiple configurations.
5. Configuration pricing is calculated using the latest available part prices.
6. Users access the system through the Angular frontend.
7. Authentication and authorization are outside the scope of this assignment.
8. Inventory management is outside the scope of this assignment.
9. The system is intended to demonstrate pricing and configuration management functionality.
10. MySQL is available for backend persistence.

---

## 3. Pseudocode for Price Breakdown Calculation

FUNCTION CalculatePriceBreakdown(configurationId)

```
configuration = FindConfiguration(configurationId)

IF configuration does not exist
    THROW ConfigurationNotFoundException

parts = GetPartsForConfiguration(configurationId)

totalPrice = 0
breakdownList = Empty List

FOR each part IN parts

    breakdownItem.partId = part.id
    breakdownItem.partName = part.name
    breakdownItem.price = part.price

    ADD breakdownItem TO breakdownList

    totalPrice = totalPrice + part.price

END FOR

RETURN breakdownList, totalPrice
```

END FUNCTION

---

## 4. Solution Overview

The application is designed as a full-stack solution consisting of:

### Frontend

* Angular 17
* TypeScript
* Service-based API communication
* Component-based architecture

### Backend

* Spring Boot
* Spring Web
* Spring Data JPA
* RESTful APIs

### Database

* MySQL

### Architecture

The backend follows a layered architecture:

Controller Layer
→ Service Layer
→ Repository Layer
→ Database

DTOs are used to separate API contracts from persistence entities and improve maintainability.

---

## 5. Application Interactivity

The solution includes both an interactive backend and frontend.

### Backend

REST APIs support:

* Part Management
* Configuration Management
* Price Breakdown Generation

### Frontend

The Angular frontend provides:

* Dashboard
* Part Management Module
* Configuration Builder Module
* Pricing Breakdown Module

The frontend communicates with the backend through HTTP REST APIs.

---

## 6. Development Support and Research Prompts

During development, AI-assisted tools were used primarily for brainstorming, architecture validation, troubleshooting, documentation assistance, and productivity improvements.

### Requirements Analysis

* Analyze the bicycle pricing problem statement and identify key functional requirements.
* Suggest a scalable architecture for a bicycle pricing and configuration management system.
* Identify potential edge cases in configuration creation and pricing calculations.

### System Design

* Review entity relationships between bicycle parts and configurations.
* Validate database design choices for maintainability and extensibility.
* Suggest REST API structures for CRUD operations and pricing retrieval.

### Backend Development Support

* Review Spring Boot project structure and recommend best practices.
* Explain the advantages of DTOs and layered architecture.
* Troubleshoot Maven configuration and application startup issues.
* Validate service and repository layer responsibilities.
* Review exception handling and API design decisions.

### Frontend Development Support

* Review Angular routing and navigation setup.
* Suggest approaches for organizing Angular components and services.
* Troubleshoot Angular build and dependency issues.
* Validate frontend-backend integration patterns.

### Testing and Debugging

* Diagnose runtime and configuration issues.
* Verify API endpoint behavior.
* Review Swagger configuration and API documentation.
* Validate application startup and deployment steps.

### Documentation Support

* Improve README structure and installation instructions.
* Review project documentation for completeness.
* Verify reproducibility of setup instructions.


## 7. Installation Instructions

### Prerequisites

Backend:

* Java 17+
* Maven 3.9+
* MySQL 8+

Frontend:

* Node.js 18+
* npm

---

### Database Setup

Create a MySQL database:

CREATE DATABASE bicycle_pricing;

Update database credentials in:

backend/src/main/resources/application.properties

---

### Backend Setup

Navigate to the backend directory:

cd backend

Build the application:

mvn clean install

Run the application:

mvn spring-boot:run

Backend URL:

http://localhost:8081

Swagger Documentation:

http://localhost:8081/swagger-ui/index.html

---

### Frontend Setup

Navigate to the frontend directory:

cd frontend

Install dependencies:

npm install

Run the application:

npm start

Frontend URL:

http://localhost:4200

---

## 8. Additional Notes

The solution emphasizes:

* Clean separation of concerns
* Layered Spring Boot architecture
* RESTful API design
* Maintainable frontend-backend integration
* Extensibility for future enhancements

The project was developed with a focus on readability, modularity, and ease of evaluation.
