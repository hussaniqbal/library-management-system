# library-management-system

This is a simple library management system developed using Spring Boot and H2 in-memory database. The APIs allows users to register books and borrowers, and perform actions like borrowing and returning books.

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.6.3 or later

### Installation

1. **Clone the repository:**

   sh
   git clone https://github.com/hussaniqbal/library-management-system.git
   cd library-management-system

2. **Build the project:**

  sh
  Copy code
  **mvn clean install**
  Run the application:

  sh
  Copy code
  **mvn spring-boot:run**
  The application will start on **http://localhost:9090**.

3. **Configuration**
The application uses an **in-memory H2 database** by default. The database configurations are specified in the application.yml file:

**yaml**
Copy code
spring:
  datasource:
    url: jdbc:h2:mem:library_mgmt
    driverClassName: org.h2.Driver
    username: sa
    password: password
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
  h2:
    console:
      enabled: true
      path: /h2-console
You can access the H2 database console at http://localhost:9090/h2-console.

**API Endpoints
Book Management
Register a new book**

URL: /api/books
Method: POST
Request Body:
json
Copy code
{
  "isbn": "1234567890",
  "title": "Example Book",
  "author": "John Doe"
}
**Response:**
201 Created: Book registered successfully
200 OK: Book with this ISBN already exists
Get all books

URL: /api/books
Method: GET
Response:
json
Copy code
[
  {
    "id": 1,
    "isbn": "1234567890",
    "title": "Example Book",
    "author": "John Doe"
  }
]
**Borrower Management
Register a new borrower**

URL: /api/borrowers
Method: POST
Request Body:
json
Copy code
{
  "name": "Jane Smith",
  "email": "jane.smith@example.com"
}
**Response:**
201 Created: Borrower registered successfully
400 Bad Request: Error in registration
**Get all borrowers**

URL: /api/borrowers
Method: GET
Response:
json
Copy code
[
  {
    "id": 1,
    "name": "Jane Smith",
    "email": "jane.smith@example.com"
  }
]
**Borrowing Books
Borrow a book**

URL: /api/borrow-book/borrow
Method: POST
Request Parameters:
borrowerId: ID of the borrower
bookId: ID of the book
Response:
201 Created: Book borrowed successfully
400 Bad Request: Error in borrowing the book
Return a borrowed book

URL: /api/borrow-book/return
Method: POST
Request Parameters:
loanId: ID of the loan
**Response:**
200 OK: Book returned successfully
400 Bad Request: Error in returning the book
**Data Models**
**Book**
id: Unique identifier for the book
isbn: ISBN number of the book
title: Title of the book
author: Author of the book
**Borrower**
id: Unique identifier for the borrower
name: Name of the borrower
email: Email address of the borrower
**BorrowBook**
id: Unique identifier for the borrowing record
borrower: The borrower who borrowed the book
book: The book that was borrowed
returned: Boolean indicating whether the book has been returned
**Assumptions**
Multiple copies of books with the same ISBN number are allowed and are registered as different books with unique IDs.
No more than one borrower can borrow the same book (same book ID) at a time.
**Error Handling**
The API uses standard HTTP status codes to indicate the success or failure of an API request. Common status codes include:

201 Created: The request has been fulfilled and resulted in a new resource being created.
200 OK: The request has succeeded.
400 Bad Request: The server could not understand the request due to invalid syntax.
