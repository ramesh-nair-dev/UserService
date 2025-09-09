---

# 🔐 User Service – Authentication & Authorization Microservice

A production-grade **Authentication & Authorization microservice** built with **Spring Boot, Spring Security, and JWT**.
This service is designed as the **entry point for user management in a microservices architecture**, handling **secure authentication, authorization, role-based access control, and token lifecycle management**.

---

## 🚀 Features Implemented

### 🔑 Authentication & Authorization

* **JWT-based authentication** → stateless, secure, and scalable.
* **Role-based access control (RBAC)** → supports `USER`, `ADMIN`, and extendable roles.
* **Custom UserDetails & GrantedAuthority** for fine-grained Spring Security integration.

### 🧩 API Endpoints

* `POST /auth/signup` → Register new users.
* `POST /auth/login` → Authenticate users & issue JWT.
* `POST /auth/logout` → Invalidate active token.
* (Extendable) → Additional secure endpoints can enforce role-based access.

### 📦 DTOs & Structured Communication

* `SignupDTO` & `LoginDTO` → Clean request models.
* `UserDTO` → Safe response without leaking sensitive data.
* `ResponseStatus` → Consistent API response wrapper.

### ⚖️ Exception Handling

* **Custom exceptions** (`UserNotExistsException`, `InvalidTokenException`, `RoleNotFoundException`) for clear error semantics.
* **Global Exception Handler** → Maps exceptions to meaningful HTTP responses.

### 🔒 Security Layer

* **Custom Security Configuration** using Spring Security.
* **JWT Token validation filter** integrated into request lifecycle.
* **Authorization model** (Role, Authority, OAuth2-like structures) ready for enterprise-grade extensions.

### 🗄️ Persistence Layer

* **Entities**: `User`, `Role`, `Token`.
* **Repositories**: JPA-based persistence for scalable relational database usage.
* **Token storage** → Enables refresh & logout functionality beyond stateless JWT.

---

## 🏗️ Tech Stack

* **Spring Boot** (core framework)
* **Spring Security** (authentication & authorization)
* **JWT (JSON Web Token)** for stateless auth
* **Hibernate/JPA** for persistence
* **Maven** for dependency management

---

## 📂 Project Structure

```
UserService/
 ├── controller/         # REST Controllers (Auth APIs)
 ├── dto/                # DTOs (Login, Signup, UserDTO, ResponseStatus)
 ├── exception/          # Custom exceptions + Global handler
 ├── model/              # Entities (User, Role, Token, Authorization models)
 ├── repository/         # Spring Data JPA repositories
 ├── security/           # JWT, CustomUserDetails, Security Config
 └── service/            # Business logic (UserService, AuthService, etc.)
```

---

## 🔥 Why This Project Stands Out

Unlike typical CRUD-based user management demos, this project implements **real-world authentication patterns**:

* JWT-based **stateless sessions** (scalable for distributed systems).
* **Separation of concerns** via layered architecture (Controller → Service → Repository).
* **Enterprise-grade exception handling** for reliability.
* **Extendable RBAC & OAuth2-inspired models**, making it a strong foundation for **production identity services**.

---

## 🛠️ Setup & Run

```bash
# Clone repo
git clone https://github.com/ramesh-nair-dev/UserService.git
cd UserService

# Build & run
mvn spring-boot:run
```

Service runs at: **`http://localhost:8080`**


## 📌 Future Enhancements

* Refresh tokens with sliding expiration.
* OAuth2 & social logins.
* API Gateway integration in a microservices setup.
* Rate limiting & audit logging for security compliance.

---

## 👨‍💻 Author

**Ramesh Nair**

* Backend Engineer | Java | Spring Boot | System Design Enthusiast
* Focused on building **scalable, maintainable, real-world systems**.
* Passionate about **clean architecture, design patterns, and domain modeling**.

📫 Reach me at: ramesh200212@gmail.com
🌐 GitHub: https://github.com/ramesh-nair-dev

---
