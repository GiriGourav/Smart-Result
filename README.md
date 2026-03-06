# 📊 Smart Result Management System

A **Spring Boot based Result Management System** that allows administrators to manage student records, upload marks, and generate results efficiently.

This project demonstrates a **layered backend architecture using Spring Boot, JPA, and REST APIs**.

---

## 🚀 Features

### 👨‍🎓 Student Management
- Add new students
- Store student information
- Retrieve student records

### 📝 Marks Management
- Upload marks for students
- Store subject-wise marks
- Update marks easily

### 📄 Result Generation
- Calculate total marks
- Generate student result
- Display results dynamically

### 📂 Answer Sheet Upload
- Upload answer sheets
- Store uploaded files
- Link answer sheets with student records

---

## 🏗️ Project Architecture

The project follows a **layered architecture**:

```
Controller → Service → Repository → Database
```

### Controller Layer
Handles HTTP requests and responses.

```
controllers/
 ├── PageController.java
 └── ResultController.java
```

### Service Layer
Contains business logic.

```
service/
 └── ResultService.java
```

### Repository Layer
Handles database operations.

```
repository/
 ├── StudentRepo.java
 └── MarkRepo.java
```

### Entity Layer
Represents database tables.

```
entities/
 ├── Student.java
 └── Mark.java
```

### Payload / DTO Layer
Handles request data.

```
payload/
 ├── StudentForm.java
 └── MarkForm.java
```

---

## 🛠️ Tech Stack

- **Java**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **MySQL / H2 Database**
- **Maven**
- **HTML / Thymeleaf**

---

## 📁 Project Structure

```
Smart-Result
│
├── src/main/java/com/substring/smartresult
│
├── config
│
├── controllers
│   ├── PageController.java
│   └── ResultController.java
│
├── entities
│   ├── Mark.java
│   └── Student.java
│
├── payload
│   ├── MarkForm.java
│   └── StudentForm.java
│
├── repository
│   ├── MarkRepo.java
│   └── StudentRepo.java
│
├── service
│   └── ResultService.java
│
├── SmartResultApplication.java
│
└── resources
```

---

## ⚙️ Installation & Setup

### 1. Clone the repository

```bash
git clone https://github.com/GiriGourav/Smart-Result.git
```

### 2. Navigate to the project folder

```bash
cd Smart-Result
```

### 3. Build the project

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

---

## 🌐 API Endpoints

| Method | Endpoint | Description |
|------|------|------|
| GET | /students | Get all students |
| POST | /students | Add new student |
| POST | /marks | Upload student marks |
| GET | /result/{id} | Get student result |

---

## 📦 Future Improvements

- Authentication using Spring Security
- Admin Dashboard
- PDF Result Generation
- Email Result Notification
- Search and Pagination

---

## 👨‍💻 Author

**Gourav Giri**

GitHub:  
https://github.com/GiriGourav
