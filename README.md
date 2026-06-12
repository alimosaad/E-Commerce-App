# 🛒 E-Commerce App

A modern, scalable microservices-based e-commerce application built with **Spring Boot**, **Spring Cloud**, and **Kafka**. This project demonstrates enterprise-grade architecture patterns for handling product management, customer management, order processing, and payment operations.

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Technology Stack](#technology-stack)
- [Services](#services)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Database Setup](#database-setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Key Features](#key-features)
- [Communication Patterns](#communication-patterns)
- [Contributing](#contributing)

---

## 📌 Project Overview

The E-Commerce App is a distributed microservices architecture designed for scalability, maintainability, and resilience. Each service is independently deployable and handles a specific business domain following the Domain-Driven Design (DDD) principles.

**Key Highlights:**
- ✅ Microservices architecture with Spring Boot
- ✅ Service discovery using Eureka
- ✅ API Gateway for request routing
- ✅ Asynchronous messaging with Apache Kafka
- ✅ Multiple database support (PostgreSQL, MongoDB)
- ✅ Email notifications via Kafka
- ✅ RESTful API design

---

## 🏗️ Architecture

```
┌─────────────┐
│  API Gateway│
└──────┬──────┘
       │
    ┌──┴──────────────────────────────────┐
    │                                      │
┌───▼────┐ ┌──────────┐ ┌─────────┐ ┌───▼────┐
│Customer │ │  Product │ │  Order  │ │Payment │
│Service  │ │ Service  │ │Service  │ │Service │
└───┬────┘ └──────────┘ └─────────┘ └───┬────┘
    │                                    │
    └──────────────┬─────────────────────┘
                   │
            ┌──────▼──────┐
            │ Kafka Topics│
            │             │
            │• order-topic│
            │• payment-   │
            │  topic      │
            └─────────────┘
                   │
         ┌─────────┴─────────┐
         │                   │
    ┌────▼─────┐      ┌─────▼────┐
    │PostgreSQL │      │  MongoDB  │
    │(Customer, │      │(Product)  │
    │Order)     │      │           │
    └───────────┘      └───────────┘

┌──────────────────────────────────┐
│  Service Discovery (Eureka)      │
│  Centralized Configuration       │
└──────────────────────────────────┘
```

---

## 🛠️ Technology Stack

### Backend
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Cloud** (Eureka, Feign, Config)
- **Spring Data JPA**

### Databases
- **PostgreSQL** - Customer and Order data
- **MongoDB** - Product data

### Messaging & Event Streaming
- **Apache Kafka** - Asynchronous communication
- **Zookeeper** - Kafka coordination

### Additional Services
- **Eureka Server** - Service Discovery
- **API Gateway** - Request routing and load balancing
- **Mail Dev** - Email development testing

### Build & Development
- **Maven** - Build automation
- **Docker** - Containerization
- **Docker Compose** - Orchestration

---

## 🏢 Services

### 1. **API Gateway** 🚪
- Entry point for all client requests
- Routes requests to appropriate microservices
- Handles cross-cutting concerns

**Port:** 8888

---

### 2. **Customer Service** 👥
Manages customer information and operations.

**Endpoints:**
- `POST /api/v1/customer` - Create a new customer
- `PUT /api/v1/customer` - Update customer details
- `GET /api/v1/customer` - List all customers
- `GET /api/v1/customer/{customer-id}` - Get customer by ID
- `GET /api/v1/customer/exists/{customer-id}` - Check if customer exists
- `DELETE /api/v1/customer/{customer-id}` - Delete a customer

**Database:** PostgreSQL

**Port:** 8070

---

### 3. **Product Service** 📦
Handles product catalog management and inventory.

**Endpoints:**
- `POST /api/v1/product` - Create a new product
- `POST /api/v1/product/purchase` - Purchase products (inventory deduction)
- `GET /api/v1/product` - List all products
- `GET /api/v1/product/{product-id}` - Get product by ID

**Database:** MongoDB

**Port:** 8050

**Features:**
- Product inventory management
- Purchase request handling with quantity validation
- Exception handling for insufficient inventory

---

### 4. **Order Service** 📋
Manages customer orders and order lines.

**Endpoints:**
- `POST /api/v1/orders` - Create a new order
- `GET /api/v1/orders` - List all orders
- `GET /api/v1/orders/{order-id}` - Get order by ID
- `GET /api/v1/order-lines/order/{order-id}` - Get order lines for an order

**Database:** PostgreSQL

**Port:** 8060

**Features:**
- Order creation with validation
- Order line management
- Audit fields (createdAt, lastModifiedDate)
- Payment method tracking

---

### 5. **Payment Service** 💳
Processes payments and sends notifications.

**Endpoints:**
- `POST /api/v1/payment` - Create a payment

**Database:** PostgreSQL

**Port:** 8080

**Features:**
- Payment processing
- Kafka integration for sending payment notifications
- Support for multiple payment methods

---

### 6. **Discovery Server** 🔍
Service registry and discovery using Netflix Eureka.

**Features:**
- All microservices register themselves
- Enables dynamic service discovery
- Provides service health monitoring

**Port:** 8761

---

### 7. **Notification Service** 📧
Handles email notifications (via Kafka topics).

**Features:**
- Listens to Kafka topics for events
- Sends email notifications via Mail Dev
- Asynchronous notification processing

---

## 📁 Project Structure

```
E-Commerce-App/
├── services/
│   ├── api-gateway/              # API Gateway Service
│   ├── customer/                 # Customer Management Service
│   ├── product/                  # Product Management Service
│   ├── order/                    # Order Management Service
│   ├── payment/                  # Payment Processing Service
│   ├── discovery-server/         # Eureka Discovery Server
│   └── notification-service/     # Email Notification Service
├── docker-compose.yml            # Docker Compose configuration
└── README.md                      # This file
```

---

## 📦 Prerequisites

Before running the application, ensure you have the following installed:

- **Java 17 or higher**
- **Maven 3.6+**
- **Docker** and **Docker Compose**
- **Git**

---

## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/alimosaad/E-Commerce-App.git
cd E-Commerce-App
```

### 2. Start Infrastructure Services

Using Docker Compose, start all required databases and message brokers:

```bash
docker-compose up -d
```

This will start:
- PostgreSQL (port 5432)
- PgAdmin (port 5050)
- MongoDB (port 27017)
- Mongo Express (port 8081)
- Zookeeper (port 22181)
- Kafka (port 9092)
- Mail Dev (port 1080 UI, 1025 SMTP)

### 3. Verify Services

Check service status:

```bash
docker-compose ps
```

---

## 🗄️ Database Setup

### PostgreSQL (Customer & Order Data)

Default credentials from `docker-compose.yml`:
- **Username:** alimosaad
- **Password:** alimosaad
- **Host:** localhost
- **Port:** 5432

**Access PgAdmin:** http://localhost:5050

### MongoDB (Product Data)

Default credentials:
- **Username:** alimosaad
- **Password:** alimosaad
- **Port:** 27017

**Access Mongo Express:** http://localhost:8081

---

## ▶️ Running the Application

### Build All Services

```bash
mvn clean install
```

### Run Services in Order

Start the services in this order to ensure proper initialization:

1. **Discovery Server (Eureka)**
   ```bash
   cd services/discovery-server
   mvn spring-boot:run
   ```

2. **Customer Service**
   ```bash
   cd services/customer
   mvn spring-boot:run
   ```

3. **Product Service**
   ```bash
   cd services/product
   mvn spring-boot:run
   ```

4. **Order Service**
   ```bash
   cd services/order
   mvn spring-boot:run
   ```

5. **Payment Service**
   ```bash
   cd services/payment
   mvn spring-boot:run
   ```

6. **API Gateway**
   ```bash
   cd services/api-gateway
   mvn spring-boot:run
   ```

### Or Use Docker (Optional)

Build Docker images for each service:

```bash
# For each service directory
docker build -t ecommerce/service-name .
docker run -p PORT:PORT ecommerce/service-name
```

---

## 📡 API Endpoints

### Base URL
```
http://localhost:8888/api/v1
```

### Customer Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/customer` | Create customer |
| PUT | `/customer` | Update customer |
| GET | `/customer` | List all customers |
| GET | `/customer/{id}` | Get customer by ID |
| GET | `/customer/exists/{id}` | Check customer existence |
| DELETE | `/customer/{id}` | Delete customer |

### Product Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/product` | Create product |
| POST | `/product/purchase` | Purchase products |
| GET | `/product` | List all products |
| GET | `/product/{id}` | Get product by ID |

### Order Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/orders` | Create order |
| GET | `/orders` | List all orders |
| GET | `/orders/{id}` | Get order by ID |
| GET | `/order-lines/order/{id}` | Get order lines |

### Payment Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/payment` | Create payment |

---

## ✨ Key Features

### 1. **Microservices Architecture**
- Independent services with separate databases
- Domain-driven design principles
- Service isolation and decoupling

### 2. **Service Discovery**
- Automatic service registration with Eureka
- Dynamic service discovery
- Health checking and monitoring

### 3. **Asynchronous Communication**
- Apache Kafka for event-driven architecture
- Decoupled services through message topics
- Reliable message delivery

### 4. **Database Flexibility**
- PostgreSQL for relational data (customers, orders)
- MongoDB for document-based data (products)
- Polyglot persistence approach

### 5. **Payment Processing**
- Secure payment handling
- Multiple payment method support
- Notification integration

### 6. **Notification System**
- Email notifications via Kafka topics
- Asynchronous processing
- Event-driven architecture

---

## 🔄 Communication Patterns

### Synchronous Communication
- REST APIs via API Gateway
- Service-to-service Feign clients
- Real-time request/response

### Asynchronous Communication
- **Kafka Topics:**
  - `order-topic` - Order events
  - `payment-topic` - Payment events
- Event producers and consumers
- Decoupled service communication

### Example Flow: Order Creation
1. Client sends POST request to API Gateway
2. Gateway routes to Order Service
3. Order Service validates customer via Customer Service (Feign)
4. Order Service validates products via Product Service (Feign)
5. Order created and event published to Kafka
6. Payment Service consumes event and processes payment
7. Notification Service sends email confirmation

---

## 🧪 Testing

Run unit and integration tests:

```bash
mvn test
```

---

## 📝 Configuration

### application.properties
Each service has its own configuration. Key properties include:

```properties
server.port=PORT
spring.datasource.url=jdbc:postgresql://localhost:5432/dbname
spring.jpa.hibernate.ddl-auto=update
spring.kafka.bootstrap-servers=localhost:9092
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

---

## 🐛 Troubleshooting

### Services not registering with Eureka
- Ensure Discovery Server is running on port 8761
- Check network connectivity between services
- Review service logs for errors

### Kafka connection issues
- Verify Zookeeper and Kafka are running
- Check Docker Compose logs: `docker-compose logs kafka`
- Ensure correct broker URLs in properties

### Database connection failures
- Verify PostgreSQL/MongoDB containers are running
- Check credentials in docker-compose.yml
- Ensure ports are not blocked

---

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Cloud Documentation](https://spring.io/projects/spring-cloud)
- [Apache Kafka Documentation](https://kafka.apache.org/documentation/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [MongoDB Documentation](https://docs.mongodb.com/)

---

## 📄 License

This project is open source and available under the MIT License.

---

## 👤 Author

**Ali Mosaad**  
GitHub: [@alimosaad](https://github.com/alimosaad)

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a new branch: `git checkout -b feature/your-feature`
3. Commit changes: `git commit -m 'Add your feature'`
4. Push to branch: `git push origin feature/your-feature`
5. Submit a Pull Request

---

## 📧 Support

For issues, questions, or suggestions, please open an issue on GitHub or contact the maintainer.

---

## 🎯 Future Enhancements

- [ ] API documentation with Swagger/OpenAPI
- [ ] Unit and integration tests for all services
- [ ] Docker Compose deployment with all services
- [ ] Authentication and authorization (OAuth2/JWT)
- [ ] Rate limiting and API throttling
- [ ] Caching layer (Redis)
- [ ] Distributed logging (ELK Stack)
- [ ] Circuit breaker pattern (Hystrix/Resilience4j)
- [ ] API versioning strategy
- [ ] GraphQL support

---

**Happy Coding! 🚀**
