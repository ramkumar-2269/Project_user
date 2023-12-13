# Project_user

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Maven

### Installing

1. Clone the repository:

   ```
   git clone https://github.com/ramkumar-2269/Project_user.git
   cd Project_user
   ```

2. Build the project
   
   ```
   mvn clean install

3. change the application.properties file
   
   ```
   spring.datasource.url={data source url}
   spring.datasource.username={username}
   spring.datasource.password={password}

4. Run the application
   
   ```
   mvn spring-boot:run

The application will be accessible at http://localhost:8080.


### API ENDPOINTS
    
--> Get All users:

    Endpoint: GET /users

    It will return all the users.
  
--> Get user by ID:
  
    Endpoint: GET /users/{id}

    It will return user with that id if exists.

--> Create user:
  
    Endpoint: POST /user
  
    Request Body:
    json
  
    {
    "name": "ram",
    "userName": "ram552",
    "email": "rahul@gmail.com"
    }

    It will return the generated random password if the username is unique.

--> Update user:

    Endpoint: PUT /users/{id}
    Request Body:
    json

    {
    "name": "Updated name",
    "userName": "updated userName",
    "email":"updated email"
    }
    
  --> Delete user:

    Endpoint: DELETE /users/{id}





