# 📬 Telegram Web Chat Application

A **Telegram-style chat web application** that allows users to register, log in, view profiles, chat privately, and exchange files. Built using Spring Boot, Spring Security, Thymeleaf, and PostgreSQL.

---

## 🚀 Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **Thymeleaf**
- **Validation**
- **PostgreSQL**
- **Lombok**

---

## 📁 Project Structure

| File                 | Description                                                      |
|----------------------|------------------------------------------------------------------|
| `LoginController`    | Handles user login and registration                              |
| `UserController`     | Displays all available users on the home page                    |
| `ChatController`     | Manages chats, file uploads/downloads, and message display       |
| `ProfileController`  | Displays user profile and handles profile photo access           |

---

## ✅ Features

- User registration with phone number and profile image validation  
- Secure login using Spring Security  
- View all users except the current logged-in user  
- Real-time private chat interface  
- Send messages with optional file attachments (images, docs, etc.)  
- View/download attached files  
- View user profiles and profile images  

---

## ▶️ How to Run

1. **Clone the Project**
   ```bash
   git clone https://github.com/your-username/telegram-web-chat.git
   cd telegram-web-chat
   ```

2. **Configure PostgreSQL**
   Open the `application.properties` file and set your database credentials:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🔐 Security Notes

- Passwords are securely encrypted using `PasswordEncoder`
- Input validation includes:
  - Unique username
  - Phone number format: `+998xxxxxxxxx`
  - Password length and matching confirmation
  - Required profile image file

---

## 🌐 Endpoints Overview

| Method | Endpoint         | Description                                |
|--------|------------------|--------------------------------------------|
| GET    | `/login`         | Show login page                            |
| POST   | `/register`      | Handle user registration                   |
| GET    | `/`              | Show all users (except the current user)   |
| GET    | `/chat?id=X`     | Open chat with user X                      |
| POST   | `/sendMessage`   | Send message (text + optional file)        |
| GET    | `/file?id=X`     | Download file attached to message          |
| GET    | `/photo?id=X`    | Get user profile photo                     |
| GET    | `/profile?id=X`  | View a user's profile                      |
