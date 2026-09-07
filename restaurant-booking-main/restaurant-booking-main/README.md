# 🍽️ Restaurant Booking System

A full-stack Restaurant Booking System built using Java, Spring Boot, Spring Data JPA, Hibernate, MySQL, Spring Security, Thymeleaf, HTML, CSS, and JavaScript.

The application allows users to explore restaurants, view menus, photos and reviews, check table availability, and make restaurant bookings. It also provides admin functionality to manage restaurants, tables, menu items, reviews, photos, and bookings.

---

## 🚀 Live Demo

🔗 **Live Website:** https://restaurant-booking-production-9aef.up.railway.app

🔗 **GitHub Repository:** https://github.com/AJ5322/restaurant-booking

---

## 📌 Features

### 👤 User Features

- View all restaurants
- View restaurant details
- View restaurant photos
- View menu items/dishes
- View restaurant reviews
- Check table availability
- Book a restaurant table
- View booking details

### 🔐 Admin Features

- Admin authentication
- Add restaurants
- Update restaurant information
- Delete restaurants
- Manage restaurant tables
- Manage menu items
- Manage restaurant photos
- Manage reviews
- Manage bookings

---

## 🛠️ Technologies Used

### Backend

- Java 17
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
- Maven

### Frontend

- Thymeleaf
- HTML5
- CSS3
- JavaScript

### Database

- MySQL

### Deployment & Tools

- Git
- GitHub
- Railway
- IntelliJ IDEA / Eclipse
- Postman

---

## 🏗️ Project Architecture

The project follows a layered architecture:

```text
                    Client / Browser
                           |
                           ↓
                     Controller
                           |
                           ↓
                       Service
                           |
                           ↓
                     Repository
                           |
                           ↓
                   Spring Data JPA
                           |
                           ↓
                       Hibernate
                           |
                           ↓
                         MySQL
