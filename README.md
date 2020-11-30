Person Service
==============

A simple JPA backend service that provides CURD operations for person details using H2 in memory database.

It can be easily upgraded to read/write from a real database instead of the H2 in memory DB

## Technology Stack

##DEV
```bash
JPA
Spring Boot
MapStruct(Code generator)
Lombok
H2 In memory
Hibernate validator
```
##Testing

```bash
Junit4
Mockito

```


## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/SindhuGeek/person-service.git
```

**2. Build the app using maven**

```bash
mvn clean install
```

**3. Run the tests using maven**

```bash
mvn test
```

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

**5. Open H2 console to check the tables**

```bash
http://localhost:8080/h2
Driver Class:org.h2.Driver
JDBC URL:jdbc:h2:mem:db
Username:sa
Password:sa
```

The app defines following CRUD APIs.

### PersonService


```bash
/api/v1/person/{id}  Get person details
/api/v1/person  Get all persons 
/api/v1/person/personCount  Get persons count
/api/v1/person  Add Person and address details
/api/v1/person  Update Person and address details 
/api/v1/person  Delete Person

```

### Sample request response

```bash
Refer to PersonService.postman_collection.json under project root folder
```


