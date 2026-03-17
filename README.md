# Library Management System

[![English](https://img.shields.io/badge/📖-English-blue)](README.md)
[![Tiếng Việt](https://img.shields.io/badge/📖-Tiếng%20Việt-green)](README.vi.md)

## 📚 Project Description

**Library Management System** is a comprehensive web application built with a full-stack architecture, including Spring Boot backend and React frontend. The system allows efficient management of books, users, and borrowing records.

### 🎯 Objectives

- Manage book information (add, edit, delete, search)
- Manage user information (readers, librarians)
- Track borrowing/returning history
- Provide a user-friendly interface
- Support Docker deployment

### ✨ Key Features

#### Backend API (Spring Boot)
- RESTful API following REST standards
- Book management: CRUD operations, search by name/author/category
- User management: CRUD operations, authorization
- Borrowing system: create borrow records, return books, extend
- Authentication and authorization (JWT)
- Data validation
- Global exception handling
- API Documentation with Swagger

#### Frontend (React)
- Modern UI with React 18
- Navigation with React Router
- State management with React Hooks
- API calls with Axios
- Responsive design for all devices
- Notifications and error handling

### 🛠️ Technologies Used

| Layer | Technology |
|-------|-----------|
| **Backend** | Java 21, Spring Boot 3.x, Spring Data JPA, Spring Security |
| **Frontend** | React 18, React Router 6, Axios, CSS3 |
| **Database** | MySQL 8.0 |
| **Build Tool** | Maven (Backend), npm (Frontend) |
| **Container** | Docker, Docker Compose, Nginx |
| **Testing** | JUnit 5, Mockito, React Testing Library |

### 📊 Database Schema

The system uses 3 main tables:
- **Books**: Store book information (ID, name, author, category, ISBN, quantity)
- **Users**: Store user information (ID, name, email, role)
- **BorrowRecords**: Store borrowing history (ID, user_id, book_id, borrow date, return date, status)

### 🔗 API Endpoints

| Module | Endpoints | Description |
|--------|-----------|-------------|
| **Books** | `GET /api/books` | Get all books |
| | `GET /api/books/{id}` | Get book by ID |
| | `POST /api/books` | Add new book |
| | `PUT /api/books/{id}` | Update book |
| | `DELETE /api/books/{id}` | Delete book |
| **Users** | `GET /api/users` | Get all users |
| | `GET /api/users/{id}` | Get user by ID |
| | `POST /api/users` | Add new user |
| | `PUT /api/users/{id}` | Update user |
| | `DELETE /api/users/{id}` | Delete user |
| **Borrow** | `GET /api/borrows` | Get all borrow records |
| | `POST /api/borrows` | Create new borrow record |
| | `PUT /api/borrows/{id}/return` | Return book |

API documentation details: [backend/doc/](backend/doc/)

## 📁 Project Structure

```
Library_Management_System/
├── backend/                 # Spring Boot Backend
│   ├── src/                # Java source code
│   │   ├── main/java/     # Main code
│   │   └── test/java/     # Unit tests
│   ├── pom.xml            # Maven configuration
│   └── Dockerfile         # Docker configuration
├── frontend/               # React Frontend
│   ├── src/               # React source code
│   │   ├── App.js         # Main component
│   │   └── index.js       # Entry point
│   ├── public/            # Static assets
│   ├── package.json       # Dependencies
│   ├── Dockerfile         # Docker configuration
│   └── nginx.conf         # Nginx configuration
├── docker-compose.yml      # Docker Compose (orchestrates all services)
└── README.md              # Documentation (English)
└── README.vi.md           # Documentation (Tiếng Việt)
```

## 🚀 Installation Guide

### Method 1: Using Docker (Recommended)

**Prerequisites:**
- Docker Desktop installed
- Docker Compose installed

**Steps:**

1. **Clone repository**
   ```bash
   git clone https://github.com/dajtkw/Library_Management_System.git
   cd Library_Management_System
   ```

2. **Start all services**
   ```bash
   docker-compose up -d
   ```

3. **Access the application**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - MySQL: localhost:3306

4. **Stop services**
   ```bash
   docker-compose down
   ```

### Method 2: Manual Installation

#### Backend (Spring Boot)

**Prerequisites:**
- Java 21+
- Maven 3.8+

**Steps:**
```bash
cd backend
./mvnw spring-boot:run
```

Backend will run at: http://localhost:8080

#### Frontend (React)

**Prerequisites:**
- Node.js 18+
- npm 9+

**Steps:**
```bash
cd frontend
npm install
npm start
```

Frontend will run at: http://localhost:3000

## 🐳 Docker Commands

```bash
# Start all services (background)
docker-compose up -d

# View logs
docker-compose logs -f

# View logs of a specific service
docker-compose logs -f backend
docker-compose logs -f frontend

# Stop all services
docker-compose down

# Restart services
docker-compose restart

# View container status
docker-compose ps

# Rebuild and start
docker-compose up -d --build
```

## 📝 Environment Variables

### Backend
| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | MySQL connection URL | `jdbc:mysql://mysql:3306/library_management` |
| `SPRING_DATASOURCE_USERNAME` | MySQL username | `library_user` |
| `SPRING_DATASOURCE_PASSWORD` | MySQL password | `library_password` |

### Frontend
| Variable | Description | Default |
|----------|-------------|---------|
| `REACT_APP_API_URL` | Backend API URL | `http://backend:8080` |

## 🧪 Testing

### Backend Tests
```bash
cd backend
./mvnw test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## 📖 API Documentation

API documentation details:
- [Book API](backend/doc/BOOK_API_DOCUMENTATION.md)
- [User API](backend/doc/USER_API_DOCUMENTATION.md)
- [Borrow API](backend/doc/BORROW_API_DOCUMENTATION.md)
- [Postman Collection](backend/doc/Library_Management_APIs_Postman_Collection.json)

## 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Create Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **dajtkw** - [GitHub](https://github.com/dajtkw)

## 📞 Contact

If you have questions or suggestions, please create an issue on GitHub.

---

**⭐ If you find this project useful, please give it a star on GitHub!**
