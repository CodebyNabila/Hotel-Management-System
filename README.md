
# Hotel Management System (Full Stack)

## Overview
This repo contains:
- `backend` — Spring Boot REST API (Java, JPA). Uses H2 in-memory DB by default.
- `frontend` — React single page app (create-react-app style).

## Run locally (development)

### 1) Backend
Open terminal in `backend/`:

```bash
# build & run (requires Maven and Java 17+)
mvn spring-boot:run
```

This starts backend on `http://localhost:8080`.
H2 console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:hoteldb`)

### 2) Frontend
Open another terminal in `frontend/`:

```bash
npm install
npm start
```

This starts frontend on `http://localhost:3000` and it calls the backend at port 8080.

## Run with Docker+Postgres (production-like)
Adjust backend configuration to use Postgres and add Postgres dependency, then:

```bash
docker-compose up --build
```

## Notes
- The backend initializes 60 rooms (numbers 1..60) following your original mapping.
- Menu items and prices follow your original values.
- For production use, replace H2 with PostgreSQL/MySQL and enable security/authentication.
