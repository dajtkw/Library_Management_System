# Library Management System

A full-stack Library Management System with Spring Boot backend and React frontend.

## Project Structure

```
Library_Management_System/
├── backend/                 # Spring Boot Backend
│   ├── src/                # Java source code
│   ├── pom.xml            # Maven configuration
│   └── Dockerfile         # Backend Docker configuration
├── frontend/               # React Frontend
│   ├── src/               # React source code
│   ├── public/            # Static assets
│   ├── package.json       # Node.js dependencies
│   ├── Dockerfile         # Frontend Docker configuration
│   └── nginx.conf         # Nginx configuration
├── docker-compose.yml      # Main Docker Compose (orchestrates all services)
└── README.md              # This file
```

## Features

### Backend (Spring Boot)
- RESTful API for library management
- Book management (CRUD operations)
- User management (CRUD operations)
- Borrowing system
- MySQL database integration
- Docker support

### Frontend (React)
- Modern React 18 with hooks
- React Router for navigation
- Axios for API calls
- Responsive design
- Docker support with Nginx

## Quick Start

### Using Docker (Recommended)

1. **Clone the repository**
   ```bash
   git clone <repository-url>
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

### Manual Setup

#### Backend
```bash
cd backend
./mvnw spring-boot:run
```

#### Frontend
```bash
cd frontend
npm install
npm start
```

## API Documentation

API documentation is available in the `backend/doc/` directory:
- [Book API Documentation](backend/doc/BOOK_API_DOCUMENTATION.md)
- [User API Documentation](backend/doc/USER_API_DOCUMENTATION.md)
- [Borrow API Documentation](backend/doc/BORROW_API_DOCUMENTATION.md)

## Environment Variables

### Backend
- `SPRING_DATASOURCE_URL`: MySQL connection URL
- `SPRING_DATASOURCE_USERNAME`: MySQL username
- `SPRING_DATASOURCE_PASSWORD`: MySQL password

### Frontend
- `REACT_APP_API_URL`: Backend API URL

## Docker Commands

```bash
# Start all services
docker-compose up -d

# Stop all services
docker-compose down

# View logs
docker-compose logs -f

# Rebuild and start
docker-compose up -d --build
```

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
