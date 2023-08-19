# File CRUD Project

File CRUD App is an backend application that creates, retrieves, updates and deletes files to/from MySQL database. Developed with below techs : 
- JDK 11
- Spring Boot
- MySQL Database
- Spring JPA - Validation
- Spring Security - JWT
- Swagger
- Lombok
- Docker

## Setup
- Install Docker and start the application
### To Run
- Run "mvn clean install" in terminal
- Run "docker-compose up" in terminal
### To Stop
- Press CTRL+C in terminal
- Run "docker-compose down" in terminal

## Entities and Fields

- File : name, size, extension, bytes;
  
- Account : username, password, role


## Controllers

### About Controllers
- Postman Collection is added to root directory. Once you run the application, you can send request in the required formats.

- All endpoints are secured. So first you need to register and then you can login with your username and password. You will receieve a token in a response body. When you send a request to other APIs you must use that token with Bearer Token type in Authorization tab. Token expires after 15 mins.

### File Controller

#### Save

```http
  POST /api/v1/file
```
A file is need in form-data ->
![](https://github.com/cihanciftci1/filecrudproject/assets/72259867/542b7655-388d-4e32-a63f-929387a95426)



#### Retrieve

```http
  GET /api/v1/file/{fileId}
```


#### Update

```http
  POST /api/v1/file/{fileId}
```
A file is need in form-data ->
![](https://github.com/cihanciftci1/filecrudproject/assets/72259867/f8e174c6-e725-4d5b-aeec-11cca281008b)



#### Delete

```http
  DELETE /api/v1/file/{fileId}
```

### Auth Controller

#### Register

```http
  POST /api/v1/auth
```

A JSON Body is needed ->     
![](https://github.com/cihanciftci1/filecrudproject/assets/72259867/76ed8870-f16b-4562-957b-be56d3532fc3)


#### Login

```http
  GET /api/v1/auth
```

A JSON Body is needed ->      
![](https://github.com/cihanciftci1/filecrudproject/assets/72259867/76ed8870-f16b-4562-957b-be56d3532fc3)


You can check also APIs in the link below after running the application :   
http://localhost:8080/swagger-ui/index.html  


### Total Unit Test Coverage
![total test coverage](https://github.com/cihanciftci1/filecrudproject/assets/72259867/89affc34-9bac-459a-8629-14e4fcd0cf84)

