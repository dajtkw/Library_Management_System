# Book Management APIs Documentation

## Base URL
```
http://localhost:8080/api/v1/books
```

## Overview
This collection provides APIs for managing books in the library management system.

---

## API Endpoints

### 1. Get All Books
**GET** `/books`

Retrieve all books from the library.

**Response (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Spring Boot in Action",
    "author": "Craig Walls",
    "isbn": "978-1617292545",
    "quantity": 5
  },
  {
    "id": 2,
    "title": "Clean Code",
    "author": "Robert C. Martin",
    "isbn": "978-0132350884",
    "quantity": 3
  }
]
```

---

### 2. Get Book by ID
**GET** `/books/{id}`

Retrieve a specific book by its ID.

**Path Parameter:**
- `id` (required): Book ID

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "isbn": "978-1617292545",
  "quantity": 5
}
```

**Error Responses:**
- `404 Not Found`: Book not found with id: {id}

---

### 3. Create a New Book
**POST** `/books`

Create a new book in the library.

**Request Body:**
```json
{
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "isbn": "978-1617292545",
  "quantity": 5
}
```

**Field Validation:**
- `title` (required): Book title
- `author` (required): Author name
- `isbn` (optional): ISBN number (must be unique if provided)
- `quantity` (required): Number of copies (must be >= 0)

**Response (201 Created):**
```json
{
  "id": 1,
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "isbn": "978-1617292545",
  "quantity": 5
}
```

**Error Responses:**
- `400 Bad Request`: Validation errors (missing required fields, invalid quantity)
- `500 Internal Server Error`: Duplicate ISBN

---

### 4. Update Book Quantity
**PUT** `/books/{id}`

Update the quantity of a specific book.

**Path Parameter:**
- `id` (required): Book ID

**Request Body:**
```json
{
  "quantity": 10
}
```

**Response (200 OK):**
```json
{
  "id": 1,
  "title": "Spring Boot in Action",
  "author": "Craig Walls",
  "isbn": "978-1617292545",
  "quantity": 10
}
```

**Error Responses:**
- `404 Not Found`: Book not found with id: {id}
- `400 Bad Request`: Quantity must be non-negative

---

### 5. Delete a Book
**DELETE** `/books/{id}`

Delete a book from the library.

**Path Parameter:**
- `id` (required): Book ID

**Response (204 No Content):**
```
(empty response body)
```

**Error Responses:**
- `404 Not Found`: Book not found with id: {id}

---

## Postman Testing Examples

### Create a Book
```
POST http://localhost:8080/api/v1/books
Content-Type: application/json

{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "isbn": "978-0134685991",
  "quantity": 10
}
```

### Get All Books
```
GET http://localhost:8080/api/v1/books
```

### Get Book by ID
```
GET http://localhost:8080/api/v1/books/1
```

### Update Book Quantity
```
PUT http://localhost:8080/api/v1/books/1
Content-Type: application/json

{
  "quantity": 15
}
```

### Delete Book
```
DELETE http://localhost:8080/api/v1/books/1
```
