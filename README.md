# 🏥 UbuzimaTrack — Smart Health Monitoring System

> **Ubuzima** means *health* in Kinyarwanda. UbuzimaTrack is a full-stack IoT-powered health monitoring platform built to track, manage, and analyze patient health data in real time.

---

## 🌍 Overview

UbuzimaTrack is a modern health management system designed for clinics, hospitals, and health administrators. It combines a sleek React frontend with a secure Spring Boot backend to deliver real-time health monitoring, user management, and role-based access control.

Whether you're an administrator managing patients or a user tracking your own vitals — UbuzimaTrack has you covered.

---

## ✨ Features

### 🔐 Authentication & Security
- JWT-based authentication with token blacklisting on logout
- Role-based access control (ADMIN / USER)
- Forced password reset for newly created accounts
- BCrypt password hashing

### 👨‍💼 Admin Dashboard
- Overview of total users, active users, system status
- Create new users (credentials auto-sent via email)
- View, manage, and delete users
- Real-time system statistics

### 📊 Health Data
- IoT-connected health data tracking
- ECG chart visualization
- Per-user health records

### 🌐 Internationalization
- Full multi-language support: **English 🇬🇧 | French 🇫🇷 | Kinyarwanda 🇷🇼**
- Instant language switching with no page reload

### 🎨 UI/UX
- Dark / Light mode toggle
- Animated typewriter effect on login
- Fully responsive design
- Real-time clock display

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Frontend | React.js, React Router, Axios |
| Backend | Spring Boot 3.2, Java 21 |
| Security | Spring Security, JWT (JJWT) |
| Database | MySQL / PostgreSQL |
| Email | JavaMailSender (SMTP) |
| Build Tool | Maven |

---

## 🚀 Getting Started

### Prerequisites
- Java 21+
- Node.js 18+
- Maven
- MySQL or PostgreSQL running locally

### Backend Setup
```bash
cd health-monitoring
# Configure your database in:
# src/main/resources/application.properties

mvn spring-boot:run
```

### Frontend Setup
```bash
cd health-dashboard
npm install
npm start
```

The app will be available at `http://localhost:3000`
The API runs at `http://localhost:8080`

---

## 📁 Project Structure


---

## 🔑 Default Roles

| Role | Access |
|------|--------|
| `ADMIN` | Full access — user management, health data, settings |
| `USER` | Personal dashboard — own health data only |

> New users receive their credentials via email and are prompted to reset their password on first login.

---

## 📸 Screenshots

> 

---

## 👨‍💻 Author

**Sano Chris Armel**

---
