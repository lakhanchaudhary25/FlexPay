# 💳 FlexPay – Transactional Financial Backend

A robust, enterprise-grade backend application that simulates a secure digital wallet system. **FlexPay** enables users to create accounts, manage wallet balances, deposit and withdraw funds, and perform ACID-compliant peer-to-peer money transfers.

> **Current Version:** V1 (Core Architecture)

This project focuses on building a production-style backend using **Spring Boot**, emphasizing clean architecture, RESTful API design, transactional consistency, and maintainable code.

---

# 📂 Project Structure

```text
FlexPay/
├── .mvn/
│   └── wrapper/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── FlexPay/
│   │   │           └── FlexPay/
│   │   │               ├── config/
│   │   │               ├── constants/
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               ├── entities/
│   │   │               ├── enums/
│   │   │               ├── exception/
│   │   │               ├── mapper/
│   │   │               ├── repository/
│   │   │               ├── services/
│   │   │               ├── util/
│   │   │               └── FlexPayApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
│   └── test/
│       └── java/
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
└── pom.xml
```

---

# 🚀 Features (V1)

### 💰 Wallet Management

* Automatic wallet creation during user registration.
* Deposit money.
* Withdraw money.
* Check wallet balance.

### 🔄 ACID-Compliant Money Transfers

* Secure peer-to-peer wallet transfers.
* Uses Spring's `@Transactional` for atomic operations.
* Hibernate Dirty Checking ensures database consistency.
* Prevents partial or inconsistent transactions.

### 📜 Transaction History

* Maintains a complete ledger of:

  * Deposits
  * Withdrawals
  * Transfers

### ⚠ Global Exception Handling

Centralized exception handling using `@RestControllerAdvice`.

Examples:

* `InsufficientBalanceException`
* `WalletNotFoundException`
* `UserNotFoundException`

Returns meaningful HTTP status codes and error responses.

### 🔒 Data Isolation

Implements the **DTO + Mapper Pattern** to separate internal database entities from API request/response models.

---

# 🛠 Tech Stack

| Category            | Technology          |
| ------------------- | ------------------- |
| Language            | Java 17+            |
| Framework           | Spring Boot 3.x     |
| Build Tool          | Maven               |
| Database            | MySQL               |
| ORM                 | Hibernate           |
| Data Access         | Spring Data JPA     |
| Validation          | Hibernate Validator |
| Password Encryption | BCrypt              |

---

# 🏗 Architecture

The project follows a layered architecture.

```text
Client
   │
Controller
   │
Service
   │
Repository
   │
MySQL Database
```

### Controller Layer

* Handles HTTP requests
* Performs request validation
* Returns `ResponseEntity<T>`

### Service Layer

* Contains business logic
* Handles transactions
* Coordinates repositories

### Repository Layer

* Database abstraction using Spring Data JPA
* CRUD operations
* Custom queries

---

# 🌐 REST API Endpoints

| Method | Endpoint                           | Description         |
| ------ | ---------------------------------- | ------------------- |
| POST   | `/users`                           | Register a new user |
| POST   | `/wallets/{walletId}/deposits`     | Deposit funds       |
| POST   | `/wallets/{walletId}/withdrawals`  | Withdraw funds      |
| POST   | `/transactions`                    | Transfer money      |
| GET    | `/wallets/{walletId}/transactions` | Transaction history |

---

# 🗺 Roadmap

## ✅ V1 – Core Backend

* User Management
* Wallet Management
* Money Transfer
* Transaction History
* Global Exception Handling
* DTO Mapping

## 🚧 V2 – Authentication & Authorization

* Spring Security
* JWT Authentication
* Role-Based Access Control (RBAC)

## 📦 V3 – Database Versioning

* Flyway Migration
* Schema Version Control

## ⚡ V4 – Concurrency

* Optimistic Locking (`@Version`)
* Race Condition Handling

## 🚀 V5 – Scalability

* Redis Caching
* Apache Kafka
* Asynchronous Transaction Processing

---

# ⚙️ Running the Project

## 1. Clone the Repository

```bash
git clone https://github.com/lakhanchaudhary25/flexpay.git
```

---

## 2. Configure MySQL

Update:

```properties
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/flexpay_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
```

---

## 3. Run the Application

Using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Or on Windows:

```bash
mvnw.cmd spring-boot:run
```

---

# 📚 Learning Objectives

This project demonstrates practical experience with:

* Spring Boot
* REST API Design
* Spring Data JPA
* Hibernate ORM
* Transaction Management
* DTO Pattern
* Layered Architecture
* Exception Handling
* Validation
* Clean Code Practices

---

# 📌 Project Status

**Version:** V1 – Core Architecture

Future versions will introduce authentication, authorization, concurrency handling, database migrations, caching, and event-driven architecture to evolve FlexPay into a production-ready financial backend.

---

> *"Building scalable backend systems one transaction at a time."*
