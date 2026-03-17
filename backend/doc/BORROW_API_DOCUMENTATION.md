# Borrow Management APIs Documentation

## Base URL
```
http://localhost:8080/api/v1/borrow
```

## Overview
This collection provides comprehensive APIs for managing book borrowing operations in the library management system.

---

## API Endpoints

### 1. Borrow a Book
**POST** `/borrow`

Borrow a book for a user. The system will:
- Validate user and book existence
- Check if book is available (quantity > 0)
- Prevent user from borrowing the same book multiple times
- Create a borrow record with 14-day due date
- Decrease book quantity by 1

**Request Body:**
```json
{
  "userId": 1,
  "bookId": 1
}
```

**Response (201 Created):**
```json
{
  "id": 1,
  "userId": 1,
  "bookId": 1,
  "username": "john_doe",
  "bookTitle": "Spring Boot in Action",
  "borrowDate": "2025-03-16T01:50:00",
  "dueDate": "2025-03-30T01:50:00",
  "returnDate": null,
  "status": "BORROWED"
}
```

**Error Responses:**
- `400 Bad Request`: Validation errors (missing userId or bookId)
- `404 Not Found`: User or book not found
- `400`: Book out of stock or user already borrowed this book

---

### 2. Return a Book
**PUT** `/borrow/{borrowRecordId}/return`

Return a borrowed book. The system will:
- Update the borrow record with return date
- Change status to "RETURNED"
- Increase book quantity by 1

**Path Parameter:**
- `borrowRecordId` (required): ID of the borrow record

**Response (200 OK):**
```json
{
  "id": 1,
  "userId": 1,
  "bookId": 1,
  "username": "john_doe",
  "bookTitle": "Spring Boot in Action",
  "borrowDate": "2025-03-16T01:50:00",
  "dueDate": "2025-03-30T01:50:00",
  "returnDate": "2025-03-20T10:30:00",
  "status": "RETURNED"
}
```

**Error Responses:**
- `404 Not Found`: Borrow record not found
- `400`: Book already returned

---

### 3. Get All Borrow Records
**GET** `/borrow`

Retrieve all borrow records from the system.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "bookId": 1,
    "username": "john_doe",
    "bookTitle": "Spring Boot in Action",
    "borrowDate": "2025-03-16T01:50:00",
    "dueDate": "2025-03-30T01:50:00",
    "returnDate": null,
    "status": "BORROWED"
  }
]
```

**Note:** Consider implementing pagination for production use with large datasets.

---

### 4. Get Borrow Record by ID
**GET** `/borrow/{id}`

Retrieve a specific borrow record by its ID.

**Path Parameter:**
- `id` (required): Borrow record ID

**Response (200 OK):**
```json
{
  "id": 1,
  "userId": 1,
  "bookId": 1,
  "username": "john_doe",
  "bookTitle": "Spring Boot in Action",
  "borrowDate": "2025-03-16T01:50:00",
  "dueDate": "2025-03-30T01:50:00",
  "returnDate": null,
  "status": "BORROWED"
}
```

**Error Responses:**
- `404 Not Found`: Borrow record not found

---

### 5. Get Borrow Records by User
**GET** `/borrow/user/{userId}`

Retrieve all borrow records for a specific user, ordered by borrow date (most recent first).

**Path Parameter:**
- `userId` (required): User ID

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "bookId": 1,
    "username": "john_doe",
    "bookTitle": "Spring Boot in Action",
    "borrowDate": "2025-03-16T01:50:00",
    "dueDate": "2025-03-30T01:50:00",
    "returnDate": null,
    "status": "BORROWED"
  }
]
```

**Error Responses:**
- `404 Not Found`: User not found

---

### 6. Get Active Borrow Records by User
**GET** `/borrow/user/{userId}/active`

Retrieve all active (not returned) borrow records for a specific user.

**Path Parameter:**
- `userId` (required): User ID

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "bookId": 1,
    "username": "john_doe",
    "bookTitle": "Spring Boot in Action",
    "borrowDate": "2025-03-16T01:50:00",
    "dueDate": "2025-03-30T01:50:00",
    "returnDate": null,
    "status": "BORROWED"
  }
]
```

**Error Responses:**
- `404 Not Found`: User not found

---

### 7. Get Borrow Records by Book
**GET** `/borrow/book/{bookId}`

Retrieve all borrow records for a specific book.

**Path Parameter:**
- `bookId` (required): Book ID

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "bookId": 1,
    "username": "john_doe",
    "bookTitle": "Spring Boot in Action",
    "borrowDate": "2025-03-16T01:50:00",
    "dueDate": "2025-03-30T01:50:00",
    "returnDate": null,
    "status": "BORROWED"
  }
]
```

---

### 8. Get Overdue Borrow Records
**GET** `/borrow/overdue`

Retrieve all overdue borrow records (dueDate < now and status = "BORROWED").

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "userId": 1,
    "bookId": 1,
    "username": "john_doe",
    "bookTitle": "Spring Boot in Action",
    "borrowDate": "2025-03-01T01:50:00",
    "dueDate": "2025-03-15T01:50:00",
    "returnDate": null,
    "status": "BORROWED"
  }
]
```

---

### 9. Get Active Borrow Count
**GET** `/borrow/stats/active-count`

Get the total count of active (not returned) borrow records.

**Response (200 OK):**
```
5
```

---

## Postman Testing Examples

### Borrow a Book
```
POST http://localhost:8080/api/v1/borrow
Content-Type: application/json

{
  "userId": 1,
  "bookId": 1
}
```

### Return a Book
```
PUT http://localhost:8080/api/v1/borrow/1/return
```

### Get All Borrow Records
```
GET http://localhost:8080/api/v1/borrow
```

### Get Borrow Record by ID
```
GET http://localhost:8080/api/v1/borrow/1
```

### Get User's Borrow History
```
GET http://localhost:8080/api/v1/borrow/user/1
```

### Get User's Active Borrows
```
GET http://localhost:8080/api/v1/borrow/user/1/active
```

### Get Book's Borrow History
```
GET http://localhost:8080/api/v1/borrow/book/1
```

### Get Overdue Records
```
GET http://localhost:8080/api/v1/borrow/overdue
```

### Get Active Borrow Count
```
GET http://localhost:8080/api/v1/borrow/stats/active-count
```

---

## Complete Workflow Example

### Step 1: Create a User
```
POST http://localhost:8080/api/v1/users
Content-Type: application/json

{
  "username": "testuser",
  "email": "test@example.com",
  "password": "password123",
  "fullName": "Test User",
  "role": "ROLE_USER"
}
```

### Step 2: Create a Book
```
POST http://localhost:8080/api/v1/books
Content-Type: application/json

{
  "title": "Test Book",
  "author": "Test Author",
  "isbn": "123-456-789",
  "quantity": 5
}
```

### Step 3: Borrow the Book
```
POST http://localhost:8080/api/v1/borrow
Content-Type: application/json

{
  "userId": 1,
  "bookId": 1
}
```

### Step 4: Check Active Borrows
```
GET http://localhost:8080/api/v1/borrow/user/1/active
```

### Step 5: Return the Book
```
PUT http://localhost:8080/api/v1/borrow/1/return
```

### Step 6: Verify Return
```
GET http://localhost:8080/api/v1/borrow/1
```
