# 📌 **Spring Boot CRUD API - Order Management System**

This project is a REST API built with **Spring Boot**, enabling management of users, orders, products, and categories. 
It uses **Spring Data JPA** for database persistence and **Lombok** to reduce code verbosity.

## 🚀 **Technologies Used**
- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- Lombok
- PostgreSQL / MySQL (configurable)
- Docker

---

## 📂 **Project Structure**
```
/src/main/java/com/example/demo
│── controllers/      # REST Controllers (UserController, OrderController, etc.)
│── dtos/             # Data Transfer Objects (UserDTO, OrderDTO, etc.)
│── models/           # JPA Entities (User, Order, Product, etc.)
│── repositories/     # Database access interfaces (UserRepository, etc.)
│── services/         # Business logic (UserService, OrderService, etc.)
└── DemoApplication   # Application entry point
```

---

## ⚙ **Project Setup**

### 1️⃣ **Clone the Repository**
```bash
git clone https://github.com/juanma151004/Parcial_CRUD.git
cd Parcial_CRUD
```

### 2️⃣ **Configure the Database**
Edit `src/main/resources/application.properties`:

```properties
spring.datasource.url= jdbc:mysql://localhost:5500/db_eam?serverTimezone=UTC&createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=2320183A6A5FB
spring.jpa.hibernate.ddl-auto=update
```


### 3️⃣ **Run the Project**
``bash
`src/main/java/com/example/demo`
"Demo2Application.java"
"press RUN between line 9 and 10"
``
The API will run at: `http://localhost:4500`

---

## 🔥 **Available Endpoints**
All responses are in `JSON` format.

### 🧑 **Users (`/users`)**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/users` | Get all users |
| `GET` | `/users/{id}` | Get a user by ID |
| `POST` | `/users` | Create a user |
| `PUT` | `/users/{id}` | Update a user |
| `DELETE` | `/users/{id}` | Delete a user |

📌 **Example `POST /users`**  
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "12345"
}
```

---

### 📦 **Products (`/products`)**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/products` | Get all products |
| `GET` | `/products/{id}` | Get a product by ID |
| `POST` | `/products` | Create a product |
| `PUT` | `/products/{id}` | Update a product |
| `DELETE` | `/products/{id}` | Delete a product |

📌 **Example `POST /products`**  
```json
{
  "name": "Laptop",
  "price": 1200.50,
  "stock": 10,
  "categoryId": 1
}
```

---

### 📑 **Orders (`/orders`)**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/orders` | Get all orders |
| `GET` | `/orders/{id}` | Get an order by ID |
| `POST` | `/orders` | Create an order |
| `PUT` | `/orders/{id}` | Update an order |
| `DELETE` | `/orders/{id}` | Delete an order |

📌 **Example `POST /orders`**  
```json
{
  "userId": 1,
  "orderDate": "2025-03-05"
}
```

---

### 🛒 **Order Details (`/order-details`)**
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET` | `/order-details` | Get all order details |
| `GET` | `/order-details/{id}` | Get order detail by ID |
| `POST` | `/order-details` | Add a product to an order |
| `PUT` | `/order-details/{id}` | Update an order detail |
| `DELETE` | `/order-details/{id}` | Delete an order detail |

📌 **Example `POST /order-details`**  
```json
{
  "orderId": 1,
  "productId": 2,
  "quantity": 3
}
```

---

## 🛠 **Common Errors and Solutions**
1️⃣ **Error:** `not-null property references a null or transient value`  
💡 **Solution:** Ensure that all required values (`userId`, `categoryId`, etc.) are provided in the request.

2️⃣ **Error:** `The given id must not be null`  
💡 **Solution:** In `PUT` or `DELETE` requests, ensure a valid `id` is included.

3️⃣ **Error:** `Cannot invoke "Category.getId()" because the return value of "Product.getCategory()" is null`  
💡 **Solution:** Make sure the `Product` has a `Category` assigned before saving it.

---

## 🏁 **Conclusion**
This project provides a functional CRUD with Spring Boot to manage users, orders, and products. You can use tools like **Postman** or **cURL** to test the endpoints.

📌 **Ready! Now you can test your API at `http://localhost:4500` 🚀**
