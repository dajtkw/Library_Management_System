# Library Management System / Hệ Thống Quản Lý Thư Viện

<div align="center">

[![English](https://img.shields.io/badge/📖-English-blue?style=for-the-badge)](#english)
[![Tiếng Việt](https://img.shields.io/badge/📖-Tiếng%20Việt-green?style=for-the-badge)](#tiếng-việt)

</div>

---

<a id="english"></a>
# 📖 English

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

### 📁 Project Structure

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
└── README.md              # This file
```

### 🚀 Installation Guide

#### Method 1: Using Docker (Recommended)

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

#### Method 2: Manual Installation

**Backend (Spring Boot)**

Prerequisites: Java 21+, Maven 3.8+

```bash
cd backend
./mvnw spring-boot:run
```

Backend will run at: http://localhost:8080

**Frontend (React)**

Prerequisites: Node.js 18+, npm 9+

```bash
cd frontend
npm install
npm start
```

Frontend will run at: http://localhost:3000

### 🐳 Docker Commands

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

### 📝 Environment Variables

**Backend**
| Variable | Description | Default |
|----------|-------------|---------|
| `SPRING_DATASOURCE_URL` | MySQL connection URL | `jdbc:mysql://mysql:3306/library_management` |
| `SPRING_DATASOURCE_USERNAME` | MySQL username | `library_user` |
| `SPRING_DATASOURCE_PASSWORD` | MySQL password | `library_password` |

**Frontend**
| Variable | Description | Default |
|----------|-------------|---------|
| `REACT_APP_API_URL` | Backend API URL | `http://backend:8080` |

### 🧪 Testing

```bash
# Backend Tests
cd backend
./mvnw test

# Frontend Tests
cd frontend
npm test
```

### 📖 API Documentation

- [Book API](backend/doc/BOOK_API_DOCUMENTATION.md)
- [User API](backend/doc/USER_API_DOCUMENTATION.md)
- [Borrow API](backend/doc/BORROW_API_DOCUMENTATION.md)
- [Postman Collection](backend/doc/Library_Management_APIs_Postman_Collection.json)

### 🤝 Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Create Pull Request

### 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### 👥 Authors

- **dajtkw** - [GitHub](https://github.com/dajtkw)

### 📞 Contact

If you have questions or suggestions, please create an issue on GitHub.

---

**⭐ If you find this project useful, please give it a star on GitHub!**

---

<div align="center">

[⬆️ Back to Top / Về Đầu Trang](#library-management-system--hệ-thống-quản-lý-thư-viện)

</div>

---

<a id="tiếng-việt"></a>
# 📖 Tiếng Việt

## 📚 Mô tả dự án

**Hệ Thống Quản Lý Thư Viện** là một ứng dụng web toàn diện được xây dựng với kiến trúc full-stack, bao gồm backend Spring Boot và frontend React. Hệ thống cho phép quản lý sách, người dùng và các phiếu mượn sách một cách hiệu quả.

### 🎯 Mục đích

- Quản lý thông tin sách (thêm, sửa, xóa, tìm kiếm)
- Quản lý thông tin người dùng (độc giả, thủ thư)
- Theo dõi lịch sử mượn/trả sách
- Cung cấp giao diện thân thiện cho người dùng
- Hỗ trợ triển khai với Docker

### ✨ Tính năng chính

#### Backend API (Spring Boot)
- RESTful API tuân thủ chuẩn REST
- Quản lý sách: CRUD operations, tìm kiếm theo tên/tác giả/thể loại
- Quản lý người dùng: CRUD operations, phân quyền
- Hệ thống mượn trả: tạo phiếu mượn, trả sách, gia hạn
- Xác thực và phân quyền (JWT)
- Validation dữ liệu
- Xử lý ngoại lệ toàn cục
- API Documentation với Swagger

#### Frontend (React)
- Giao diện người dùng hiện đại với React 18
- Điều hướng với React Router
- Quản lý state với React Hooks
- Gọi API với Axios
- Responsive design cho mọi thiết bị
- Hiển thị thông báo và xử lý lỗi

### 🛠️ Công nghệ sử dụng

| Layer | Công nghệ |
|-------|-----------|
| **Backend** | Java 21, Spring Boot 3.x, Spring Data JPA, Spring Security |
| **Frontend** | React 18, React Router 6, Axios, CSS3 |
| **Database** | MySQL 8.0 |
| **Build Tool** | Maven (Backend), npm (Frontend) |
| **Container** | Docker, Docker Compose, Nginx |
| **Testing** | JUnit 5, Mockito, React Testing Library |

### 📊 Cơ sở dữ liệu

Hệ thống sử dụng 3 bảng chính:
- **Books**: Lưu trữ thông tin sách (ID, tên, tác giả, thể loại, ISBN, số lượng)
- **Users**: Lưu trữ thông tin người dùng (ID, tên, email, vai trò)
- **BorrowRecords**: Lưu trữ lịch sử mượn trả (ID, user_id, book_id, ngày mượn, ngày trả, trạng thái)

### 🔗 API Endpoints

| Module | Endpoints | Mô tả |
|--------|-----------|--------|
| **Books** | `GET /api/books` | Lấy danh sách sách |
| | `GET /api/books/{id}` | Lấy thông tin sách theo ID |
| | `POST /api/books` | Thêm sách mới |
| | `PUT /api/books/{id}` | Cập nhật thông tin sách |
| | `DELETE /api/books/{id}` | Xóa sách |
| **Users** | `GET /api/users` | Lấy danh sách người dùng |
| | `GET /api/users/{id}` | Lấy thông tin người dùng theo ID |
| | `POST /api/users` | Thêm người dùng mới |
| | `PUT /api/users/{id}` | Cập nhật thông tin người dùng |
| | `DELETE /api/users/{id}` | Xóa người dùng |
| **Borrow** | `GET /api/borrows` | Lấy danh sách phiếu mượn |
| | `POST /api/borrows` | Tạo phiếu mượn mới |
| | `PUT /api/borrows/{id}/return` | Trả sách |

Chi tiết API documentation: [backend/doc/](backend/doc/)

### 📁 Cấu trúc dự án

```
Library_Management_System/
├── backend/                 # Spring Boot Backend
│   ├── src/                # Mã nguồn Java
│   │   ├── main/java/     # Code chính
│   │   └── test/java/     # Unit tests
│   ├── pom.xml            # Cấu hình Maven
│   └── Dockerfile         # Docker configuration
├── frontend/               # React Frontend
│   ├── src/               # Mã nguồn React
│   │   ├── App.js         # Component chính
│   │   └── index.js       # Entry point
│   ├── public/            # Static assets
│   ├── package.json       # Dependencies
│   ├── Dockerfile         # Docker configuration
│   └── nginx.conf         # Nginx configuration
├── docker-compose.yml      # Docker Compose (quản lý tất cả services)
└── README.md              # File này
```

### 🚀 Hướng dẫn cài đặt

#### Cách 1: Sử dụng Docker (Khuyến nghị)

**Điều kiện tiên quyết:**
- Docker Desktop đã được cài đặt
- Docker Compose đã được cài đặt

**Các bước:**

1. **Clone repository**
   ```bash
   git clone https://github.com/dajtkw/Library_Management_System.git
   cd Library_Management_System
   ```

2. **Khởi động tất cả services**
   ```bash
   docker-compose up -d
   ```

3. **Truy cập ứng dụng**
   - Frontend: http://localhost:3000
   - Backend API: http://localhost:8080
   - MySQL: localhost:3306

4. **Dừng services**
   ```bash
   docker-compose down
   ```

#### Cách 2: Cài đặt thủ công

**Backend (Spring Boot)**

Điều kiện tiên quyết: Java 21+, Maven 3.8+

```bash
cd backend
./mvnw spring-boot:run
```

Backend sẽ chạy tại: http://localhost:8080

**Frontend (React)**

Điều kiện tiên quyết: Node.js 18+, npm 9+

```bash
cd frontend
npm install
npm start
```

Frontend sẽ chạy tại: http://localhost:3000

### 🐳 Docker Commands

```bash
# Khởi động tất cả services (background)
docker-compose up -d

# Xem logs
docker-compose logs -f

# Xem logs của một service cụ thể
docker-compose logs -f backend
docker-compose logs -f frontend

# Dừng tất cả services
docker-compose down

# Khởi động lại services
docker-compose restart

# Xem trạng thái containers
docker-compose ps

# Rebuild và khởi động
docker-compose up -d --build
```

### 📝 Environment Variables

**Backend**
| Variable | Mô tả | Default |
|----------|--------|---------|
| `SPRING_DATASOURCE_URL` | MySQL connection URL | `jdbc:mysql://mysql:3306/library_management` |
| `SPRING_DATASOURCE_USERNAME` | MySQL username | `library_user` |
| `SPRING_DATASOURCE_PASSWORD` | MySQL password | `library_password` |

**Frontend**
| Variable | Mô tả | Default |
|----------|--------|---------|
| `REACT_APP_API_URL` | Backend API URL | `http://backend:8080` |

### 🧪 Testing

```bash
# Backend Tests
cd backend
./mvnw test

# Frontend Tests
cd frontend
npm test
```

### 📖 API Documentation

- [Book API](backend/doc/BOOK_API_DOCUMENTATION.md)
- [User API](backend/doc/USER_API_DOCUMENTATION.md)
- [Borrow API](backend/doc/BORROW_API_DOCUMENTATION.md)
- [Postman Collection](backend/doc/Library_Management_APIs_Postman_Collection.json)

### 🤝 Đóng góp

1. Fork repository
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit thay đổi (`git commit -m 'Add some AmazingFeature'`)
4. Push lên branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

### 📄 License

Dự án này được cấp phép theo MIT License - xem file [LICENSE](LICENSE) để biết thêm chi tiết.

### 👥 Tác giả

- **dajtkw** - [GitHub](https://github.com/dajtkw)

### 📞 Liên hệ

Nếu bạn có câu hỏi hoặc góp ý, vui lòng tạo issue trên GitHub.

---

**⭐ Nếu bạn thấy dự án hữu ích, hãy cho một star trên GitHub!**

---

<div align="center">

[⬆️ Về Đầu Trang / Back to Top](#library-management-system--hệ-thống-quản-lý-thư-viện)

</div>
