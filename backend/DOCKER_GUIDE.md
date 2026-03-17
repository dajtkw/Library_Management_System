# Docker Guide - Library Management System

## 📋 Tổng quan

Hướng dẫn này giúp bạn chạy dự án Library Management System bằng Docker.

## 🏗️ Kiến trúc

```
┌─────────────────┐     ┌─────────────────┐
│   Spring Boot   │────▶│     MySQL 8.0   │
│   Application   │     │    Database     │
│   (Port 8080)   │     │   (Port 3306)   │
└─────────────────┘     └─────────────────┘
```

## 📁 Các file Docker

| File | Mô tả |
|------|--------|
| `Dockerfile` | Định nghĩa cách build image cho Spring Boot app |
| `docker-compose.yml` | Định nghĩa các services (MySQL + App) |
| `.dockerignore` | Loại trừ các file không cần thiết khi build |
| `init.sql` | Script khởi tạo database (tùy chọn) |

## 🚀 Cách sử dụng

### 1. Điều kiện tiên quyết

- Docker Desktop đã được cài đặt
- Docker Compose đã được cài đặt (thường đi kèm Docker Desktop)

### 2. Kiểm tra Docker

```bash
# Kiểm tra Docker version
docker --version

# Kiểm tra Docker Compose version
docker-compose --version
```

### 3. Chạy ứng dụng

#### Cách 1: Chạy tất cả services (Khuyến nghị)

```bash
# Di chuyển vào thư mục dự án
cd library_management

# Build và chạy tất cả containers
docker-compose up -d
```

#### Cách 2: Build lại image trước khi chạy

```bash
# Build lại image và chạy
docker-compose up -d --build
```

#### Cách 3: Xem logs khi chạy

```bash
# Chạy và xem logs (không detached mode)
docker-compose up
```

### 4. Kiểm tra trạng thái

```bash
# Xem các containers đang chạy
docker-compose ps

# Xem logs của tất cả services
docker-compose logs

# Xem logs của một service cụ thể
docker-compose logs app
docker-compose logs mysql

# Follow logs (xem logs real-time)
docker-compose logs -f app
```

### 5. Dừng ứng dụng

```bash
# Dừng containers (giữ data)
docker-compose stop

# Dừng và xóa containers (giữ data)
docker-compose down

# Dừng và xóa containers + volumes (xóa tất cả data)
docker-compose down -v
```

### 6. Truy cập ứng dụng

Sau khi chạy thành công, truy cập:

- **API Base URL**: http://localhost:8080
- **MySQL**: localhost:3306

#### Test API

```bash
# Kiểm tra API có hoạt động không
curl http://localhost:8080/api/books

# Hoặc mở browser
http://localhost:8080/api/books
```

## 🔧 Cấu hình

### Thay đổi mật khẩu MySQL

Chỉnh sửa file `docker-compose.yml`:

```yaml
services:
  mysql:
    environment:
      MYSQL_ROOT_PASSWORD: your_new_root_password
      MYSQL_USER: your_new_user
      MYSQL_PASSWORD: your_new_password
```

### Thay đổi port

Chỉnh sửa file `docker-compose.yml`:

```yaml
services:
  app:
    ports:
      - "9090:8080"  # Truy cập qua port 9090
  mysql:
    ports:
      - "3307:3306"  # MySQL trên port 3307
```

## 🐛 Xử lý sự cố

### 1. Port đã được sử dụng

```bash
# Lỗi: Port 8080 is already in use
# Giải pháp: Thay đổi port trong docker-compose.yml hoặc dừng service đang dùng port
```

### 2. MySQL không khởi động được

```bash
# Xóa volume cũ và thử lại
docker-compose down -v
docker-compose up -d
```

### 3. App không kết nối được MySQL

```bash
# Kiểm tra MySQL có chạy không
docker-compose ps mysql

# Kiểm tra logs MySQL
docker-compose logs mysql

# Kiểm tra network
docker network ls
docker network inspect library_management_library_network
```

### 4. Build thất bại

```bash
# Xóa cache và build lại
docker-compose build --no-cache
docker-compose up -d
```

## 📊 Các lệnh hữu ích

### Quản lý containers

```bash
# Restart một service
docker-compose restart app

# Vào shell của container
docker exec -it library_app sh
docker exec -it library_mysql bash

# Xem resource usage
docker stats
```

### Quản lý database

```bash
# Truy cập MySQL CLI
docker exec -it library_mysql mysql -u library_user -p library_management

# Backup database
docker exec library_mysql mysqldump -u root -prootpassword library_management > backup.sql

# Restore database
docker exec -i library_mysql mysql -u root -prootpassword library_management < backup.sql
```

### Xóa tất cả

```bash
# Dừng và xóa tất cả containers, volumes, networks
docker-compose down -v --rmi all

# Xóa tất cả Docker cache
docker system prune -a
```

## 🔄 Cập nhật ứng dụng

```bash
# Pull code mới nhất (nếu dùng git)
git pull

# Build lại và chạy
docker-compose up -d --build
```

## 📝 Lưu ý

1. **Lần đầu chạy**: Quá trình build có thể mất 5-10 phút tùy thuộc vào tốc độ mạng
2. **Database**: Dữ liệu được lưu trong Docker volume `mysql_data`, tồn tại sau khi restart
3. **Logs**: Logs được lưu trong container, mất khi xóa container
4. **Production**: File này dùng cho development, cần điều chỉnh cho production

## 🌐 API Endpoints

Sau khi chạy thành công, các API có sẵn tại:

| Method | Endpoint | Mô tả |
|--------|----------|--------|
| GET | /api/books | Lấy danh sách sách |
| POST | /api/books | Thêm sách mới |
| GET | /api/books/{id} | Lấy thông tin sách |
| PUT | /api/books/{id} | Cập nhật sách |
| DELETE | /api/books/{id} | Xóa sách |
| GET | /api/users | Lấy danh sách người dùng |
| POST | /api/users | Thêm người dùng mới |
| POST | /api/borrow | Mượn sách |
| POST | /api/borrow/return | Trả sách |

Xem thêm chi tiết trong thư mục `doc/`.
