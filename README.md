# Bicycle Pricing Engine

This workspace contains a full-stack bicycle pricing engine with:

- Java Spring Boot backend
- MySQL database
- JPA entities and REST APIs
- Angular frontend for part management, configuration building, and pricing breakdown

## Folders

- `backend/` — Spring Boot service
- `frontend/` — Angular application

## Backend Setup

1. Create a MySQL database:
   ```sql
   CREATE DATABASE bicycle_pricing;
   ```
2. Update `backend/src/main/resources/application.properties` with your MySQL credentials.
3. Build and run:
   ```bash
   cd backend
   mvn clean spring-boot:run
   ```

## Frontend Setup

1. Install dependencies:
   ```bash
   cd frontend
   npm install
   ```
2. Run the Angular app:
   ```bash
   npm start
   ```

## API Endpoints

- `GET /api/parts`
- `GET /api/parts/{id}`
- `POST /api/parts`
- `PUT /api/parts/{id}`
- `DELETE /api/parts/{id}`
- `PUT /api/parts/{id}/price`
- `GET /api/configurations`
- `GET /api/configurations/{id}`
- `POST /api/configurations`
- `PUT /api/configurations/{id}`
- `DELETE /api/configurations/{id}`
- `GET /api/configurations/{id}/breakdown`

## Notes

The backend stores price history every time a part price changes and calculates configuration totals with a component-wise breakdown.
