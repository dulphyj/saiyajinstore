# 🔧 Saiyajin Store - Backend

This is the backend of the **Saiyajin Store** project, a Spring Boot application that exposes a RESTful API for an e-commerce platform. It handles authentication, product and category management, order processing, image uploads via Cloudinary, and payments via PayPal.

---

## 🧰 Technologies & Dependencies

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Web
- Spring Security
- JSON Web Tokens (JWT)
- PostgreSQL
- Lombok
- MapStruct
- Swagger/OpenAPI
- Cloudinary SDK
- PayPal SDK
- Maven

---

## 🛠️ Architecture

The backend follows **Hexagonal Architecture**, separating core logic from external layers such as the database or the web layer.

---

## ⚙️ Configuration

The application uses environment variables to configure the database, security, and third-party services. These values are loaded from a `.env` file (used by Docker Compose) or directly in your IDE.

### Sample `.env` file

```env
# Port
PORT=8080

# Database
DB_URL=jdbc:postgresql://saiyajin-db:5432/saiyajin
DB_USER=user
DB_PASSWORD=password

# Cloudinary
CLOUDINARY_CLOUD_NAME=your-cloud-name
CLOUDINARY_API_KEY=your-api-key
CLOUDINARY_API_SECRET=your-api-secret

# PayPal
PAYPAL_CLIENT_ID=your-paypal-id
PAYPAL_CLIENT_SECRET=your-paypal-secret

# JWT
JWT_SECRET=your-jwt-secret
JWT_EXPIRATION=600000
```

---

## ▶️ Running the Backend

**Docker**

If you're using Docker Compose (recommended):

```bash
docker-compose up --build
```

Manually (Dev Profile)
Make sure PostgreSQL is running.

Set the profile in application.properties:

spring.profiles.active=dev
Update the file src/main/resources/application-dev.properties with your config values.

Run the app:

```bash
./mvnw spring-boot:run
```

Or if Maven is installed globally:

```bash
mvn spring-boot:run
```

---

## 🔐 Security

- Route protection using JWT

- Login returns a token

- Token must be included in Authorization header for protected routes

- Token expiration is configurable via JWT_EXPIRATION

---

## 🔍 API Documentation

Access Swagger UI locally after starting the backend:

📄 http://localhost:8080/swagger-ui/index.html

## 🧪 Testing

### ✅ Test Coverage

```bash
Component           Test Type           Status          Notes
Service classes     Unit Test           ✅ Yes          Uses Mockito to mock repository
CRUDRepositoryImpl  Unit/Integration    ✅ Yes          Can be tested with mocks or H2 DB
```

---

### 🧪 Running Tests

With wrapper:

```bash
./mvnw test
```

Or with global Maven:

```bash
mvn test
```

---

## 📂 Project Structure

```bash
backend/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/saiyajinstore/...
│ │ └── resources/
│ │ ├── application.properties
│ │ └── application-dev.properties
│ └── test/
├── Dockerfile
├── README.md
└── pom.xml
```

---

## ✅ Tips

Add your MapStruct version in the pom.xml if needed.

Make sure to use the correct profile and keep secrets out of version control.
