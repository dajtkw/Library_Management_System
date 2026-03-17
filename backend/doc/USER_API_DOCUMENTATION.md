# User Management APIs Documentation

## Base URL
```
http://localhost:8080/api/v1/users
```

## Overview
This collection provides APIs for managing users in the library management system.

---

## API Endpoints

### 1. Get All Users
**GET** `/users`

Retrieve all users from the system.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "username": "john_doe",
    "email": "john@example.com",
    "fullName": "John Doe",
    "role": "ROLE_USER"
  },
  {
    "id": 2,
    "username": "admin",
    "email": "admin@library.com",
    "fullName": "Admin User",
    "role": "ROLE_ADMIN"
  }
]
```

---

### 2. Get User by ID
**GET** `/users/{id}`

Retrieve a specific user by their ID.

**Path Parameter:**
- `id` (required): User ID

**Response (200 OK):**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "role": "ROLE_USER"
}
```

**Error Responses:**
- `404 Not Found`: User not found with id: {id}

---

### 3. Create a New User
**POST** `/users`

Create a new user in the system.

**Request Body:**
```json
{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "fullName": "John Doe",
  "role": "ROLE_USER"
}
```

**Field Validation:**
- `username` (required): Username (must be unique)
- `email` (required): Email address (must be unique)
- `password` (required): Password
- `fullName` (required): Full name
- `role` (optional): User role (default: "ROLE_USER")

**Response (201 Created):**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "john@example.com",
  "fullName": "John Doe",
  "role": "ROLE_USER"
}
```

**Error Responses:**
- `400 Bad Request`: Validation errors (missing required fields)
- `500 Internal Server Error`: Duplicate username or email

---

### 4. Update User
**PUT** `/users/{id}`

Update user information.

**Path Parameter:**
- `id` (required): User ID

**Request Body:**
```json
{
  "email": "newemail@example.com",
  "fullName": "John Updated Doe"
}
```

**Field (all optional):**
- `email`: New email address
- `fullName`: New full name

**Response (200 OK):**
```json
{
  "id": 1,
  "username": "john_doe",
  "email": "newemail@example.com",
  "fullName": "John Updated Doe",
  "role": "ROLE_USER"
}
```

**Error Responses:**
- `404 Not Found`: User not found with id: {id}
- `400 Bad Request`: Validation errors

---

### 5. Delete a User
**DELETE** `/users/{id}`

Delete a user from the system.

**Path Parameter:**
- `id` (required): User ID

**Response (204 No Content):**
```
(empty response body)
```

**Error Responses:**
- `404 Not Found`: User not found with id: {id}

---

## Postman Testing Examples

### Create a User
```
POST http://localhost:8080/api/v1/users
Content-Type: application/json

{
  "username": "jane_smith",
  "email": "jane@example.com",
  "password": "securepass123",
  "fullName": "Jane Smith",
  "role": "ROLE_USER"
}
```

### Create an Admin User
```
POST http://localhost:8080/api/v1/users
Content-Type: application/json

{
  "username": "librarian",
  "email": "librarian@library.com",
  "password": "admin123",
  "fullName": "Library Admin",
  "role": "ROLE_ADMIN"
}
```

### Get All Users
```
GET http://localhost:8080/api/v1/users
```

### Get User by ID
```
GET http://localhost:8080/api/v1/users/1
```

### Update User
```
PUT http://localhost:8080/api/v1/users/1
Content-Type: application/json

{
  "email": "updated@example.com",
  "fullName": "Updated Name"
}
```

### Delete User
```
DELETE http://localhost:8080/api/v1/users/1
```
