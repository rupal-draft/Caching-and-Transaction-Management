
# Employee Management Project

This project is a Spring Boot-based Employee Management System designed to demonstrate key concepts in caching and concurrent transaction management in a database. The application supports CRUD (Create, Read, Update, Delete) operations for employee data, leveraging caching mechanisms and transaction management to optimize performance and ensure data consistency.

## Technologies Used
- **Java**: Programming language used to develop the application.
- **Spring Boot**: Framework for building the backend application.
- **Redis**: Caching solution for improving read performance.
- **RedisInsight**: A GUI tool for managing Redis data, helping you visualize and interact with Redis in a more intuitive way.
- **PostgreSQL**: Database for storing employee data.
- **DBeaver**: Database management tool for PostgreSQL.
  
## Key Concepts Learned

### Caching
Caching temporarily stores frequently accessed data in memory, which reduces database load and improves application response times. Here’s what was covered in this project:
- **Spring Boot Default Caching**: Spring Boot’s simple caching implementation.
- **Redis Cache in Spring Boot**: Setting up Redis to cache data, providing faster access to read-heavy data.
  
### Transactions
Transactions ensure that a series of database operations are completed successfully and adhere to the ACID properties:
- **Atomicity**: Ensures all operations within a transaction are completed; if one fails, all are rolled back.
- **Consistency**: Ensures the database remains in a consistent state before and after the transaction.
- **Isolation**: Ensures that transactions are isolated from one another to prevent data corruption.
- **Durability**: Ensures that once a transaction is committed, it remains saved even in case of a system failure.

### Concurrent Transactions Management
Managing multiple transactions concurrently to ensure data consistency is achieved through:
- **Transaction Isolation Levels**: Defining the degree to which one transaction must be isolated from resource or data modifications made by other transactions.
- **`@Transactional` Annotation in Spring Boot**: Simplifies transaction management by providing declarative control over transactions in service methods.
- **Optimistic and Pessimistic Locks in Spring Data JPA**: Controls concurrent access to data, where:
  - **Optimistic Locking**: Assumes that no other transactions will conflict.
  - **Pessimistic Locking**: Prevents other transactions from modifying data while one transaction is in progress.

## Project Structure

The project is structured into controllers, services, and repository layers with caching and transaction management at its core.

### Application Properties
Configure your Redis and PostgreSQL settings in the `application.properties` file as follows:
```properties
spring.cache.type=redis
spring.data.redis.host=<your_redis_host>
spring.data.redis.port=<your_redis_port>
spring.data.redis.password=<your_redis_password>

spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### CRUD Operations
- **Create**: Add a new employee.
- **Read**: Retrieve employee data with caching for faster read times.
- **Update**: Modify existing employee data.
- **Delete**: Remove employee data from the database.

## RedisInsight Tool
[RedisInsight](https://redis.com/redis-enterprise/redis-insight/) is used to interact with the Redis database. RedisInsight provides a graphical interface to explore cached data, monitor performance, and manage Redis efficiently, which can be helpful for understanding caching and key-value data management.

## Running the Application
1. Clone the repository and open it in your IDE.
2. Configure your Redis and PostgreSQL database details in `application.properties`.
3. Run the application with `mvn spring-boot:run` or by running the main class in your IDE.
4. Use DBeaver to monitor PostgreSQL data if desired.

## Endpoints
- **POST /employees** - Add a new employee
- **GET /employees** - Get all employees (uses caching)
- **GET /employees/{id}** - Get a specific employee by ID
- **PUT /employees/{id}** - Update an employee’s information
- **DELETE /employees/{id}** - Delete an employee

## Future Improvements
- Integrate advanced caching strategies based on application needs.
- Improve concurrent transaction handling for more complex scenarios.

---

This project provides a solid foundation in caching and transaction management with Spring Boot and Spring Data JPA, using Redis and PostgreSQL as the database and caching solutions.
