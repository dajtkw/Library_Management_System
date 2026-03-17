# Hệ Thống Quản Lý Thư Viện

[![English](https://img.shields.io/badge/📖-English-blue)](README.md)
[![Tiếng Việt](https://img.shields.io/badge/📖-Tiếng%20Việt-green)](README.vi.md)

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

## 📁 Cấu trúc dự án

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
└── README.md              # Tài liệu (English)
└── README.vi.md           # Tài liệu (Tiếng Việt)
```

## 🚀 Hướng dẫn cài đặt

### Cách 1: Sử dụng Docker (Khuyến nghị)

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

### Cách 2: Cài đặt thủ công

#### Backend (Spring Boot)

**Điều kiện tiên quyết:**
- Java 21+
- Maven 3.8+

**Các bước:**
```bash
cd backend
./mvnw spring-boot:run
```

Backend sẽ chạy tại: http://localhost:8080

#### Frontend (React)

**Điều kiện tiên quyết:**
- Node.js 18+
- npm 9+

**Các bước:**
```bash
cd frontend
npm install
npm start
```

Frontend sẽ chạy tại: http://localhost:3000

## 🐳 Docker Commands

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

## 📝 Environment Variables

### Backend
| Variable | Mô tả | Default |
|----------|--------|---------|
| `SPRING_DATASOURCE_URL` | MySQL connection URL | `jdbc:mysql://mysql:3306/library_management` |
| `SPRING_DATASOURCE_USERNAME` | MySQL username | `library_user` |
| `SPRING_DATASOURCE_PASSWORD` | MySQL password | `library_password` |

### Frontend
| Variable | Mô tả | Default |
|----------|--------|---------|
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

Chi tiết API documentation có tại:
- [Book API](backend/doc/BOOK_API_DOCUMENTATION.md)
- [User API](backend/doc/USER_API_DOCUMENTATION.md)
- [Borrow API](backend/doc/BORROW_API_DOCUMENTATION.md)
- [Postman Collection](backend/doc/Library_Management_APIs_Postman_Collection.json)

## 🤝 Đóng góp

1. Fork repository
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit thay đổi (`git commit -m 'Add some AmazingFeature'`)
4. Push lên branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## 📄 License

Dự án này được cấp phép theo MIT License - xem file [LICENSE](LICENSE) để biết thêm chi tiết.

## 👥 Tác giả

- **dajtkw** - [GitHub](https://github.com/dajtkw)

## 📞 Liên hệ

Nếu bạn có câu hỏi hoặc góp ý, vui lòng tạo issue trên GitHub.

---

**⭐ Nếu bạn thấy dự án hữu ích, hãy cho một star trên GitHub!**
