# Veterinary Management System


## PROJECT SUMMARY WITH A YOUTUBE VIDEO

[![Veterinary Management System](http://img.youtube.com/vi/eS9OOPD7KwQ/0.jpg)](http://www.youtube.com/watch?v=eS9OOPD7KwQ "Veterinary Management System")



## Veterinary Management System UML Diagram

![UML-Project.png](..%2FUML-Project.png)


### Database Tables

- **vaccines**

![vaccines.png](..%2Fvaccines.png)

- **doctors**

![doctors.png](..%2Fdoctors.png)


- **customers**


![customers.png](..%2Fcustomers.png)


- **available_dates**

![availabledate_dates.png](..%2Favailabledate_dates.png)


- **appointments**


![appointments.png](..%2Fappointments.png)



- **animals**


![animals.png](..%2Fanimals.png)








## Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- Mapstruct
- Postman
- Swagger

## Project Structure

The project includes the following components:

- **ENTITY**
- **REPOSITORY**
- **DTO** / REQUEST-RESPONSE
- **MAPPER**
- **SERVICE**
- **CONTROLLER**

## Running the Application

Since the project is developed with Spring Boot, you can follow the steps below to run the application:

1. Clone the source code of the project to your computer.
2. Create your PostgreSQL database and update the connection information in the `application.properties` file.
3. Open the project in an IDE (IntelliJ IDEA, Eclipse, etc.).
4. Find the `VeterinaryApp` class and run it.

Once the application is started, you can access the API at [http://localhost:8081](http://localhost:8081).

## Endpoints

Below is a list of basic endpoints provided by the API:

| Endpoint                                     | HTTP Method | Description                                                            |
|----------------------------------------------|:------------|------------------------------------------------------------------------|
| **vaccines**                                 |             |                                                                        |
| `/api/v1/vaccines/{id}`                      | GET         | Get the vaccine with the specified ID                                   |
| `/api/v1/vaccines/{id}`                      | PUT         | Update the vaccine with the specified ID                                |
| `/api/v1/vaccines/{id}`                      | DELETE      | Delete the vaccine with the specified ID                                |
| `/api/v1/vaccines`                           | GET         | Get all vaccines                                                       |
| `/api/v1/vaccines`                           | POST        | Add a new vaccine                                                      |
| `/api/v1/vaccines/finishDate`                | GET         | Get vaccine records for the given date range                           |
| `/api/v1/vaccines/findByAnimalId/{animalId}` | GET         | Get all vaccine records for a specific animal                          |
|                                              |             |                                                                        |
| **doctors**                                  |             |                                                                        |
| `/api/v1/doctors/{id}`                       | GET         | Get the doctor with the specified ID                                    |
| `/api/v1/doctors/{id}`                       | PUT         | Update the doctor with the specified ID                                |
| `/api/v1/doctors/{id}`                       | DELETE      | Delete the doctor with the specified ID                                |
| `/api/v1/doctors`                            | GET         | Get all doctors                                                        |
| `/api/v1/doctors`                            | POST        | Add a new doctor                                                       |
|                                              |             |                                                                        |
| **customers**                                |             |                                                                        |
| `/api/v1/customers/{id}`                     | GET         | Get the pet owner with the specified ID                                 |
| `/api/v1/customers/{id}`                     | PUT         | Update the pet owner with the specified ID                             |
| `/api/v1/customers/{id}`                     | DELETE      | Delete the pet owner with the specified ID                             |
| `/api/v1/customers`                          | GET         | Get all pet owners                                                     |
| `/api/v1/customers`                          | POST        | Add a new pet owner                                                    |
| `/api/v1/customers/byName`                   | GET         | Get pet owners by name                                                 |
|                                              |             |                                                                        |
| **available_dates**                          |             |                                                                        |
| `/api/v1/available_dates/{id}`               | GET         | Get the available date with the specified ID                           |
| `/api/v1/available_dates/{id}`               | PUT         | Update the available date with the specified ID                       |
| `/api/v1/available_dates/{id}`               | DELETE      | Delete the available date with the specified ID                       |
| `/api/v1/available_dates`                    | GET         | Get all available dates                                                |
| `/api/v1/available_dates`                    | POST        | Add a new available date                                               |
|                                              |             |                                                                        |
| **appointments**                             |             |                                                                        |
| `/api/v1/appointments/{id}`                  | GET         | Get the appointment with the specified ID                              |
| `/api/v1/appointments/{id}`                  | PUT         | Update the appointment with the specified ID                          |
| `/api/v1/appointments/{id}`                  | DELETE      | Delete the appointment with the specified ID                          |
| `/api/v1/appointments`                       | GET         | Get all appointments                                                   |
| `/api/v1/appointments`                       | POST        | Add a new appointment                                                  |
| `/api/v1/appointments/doctorId`              | GET         | Get appointments based on the user-entered date range and doctor       |
| `/api/v1/appointments/animalId`              | GET         | Get appointments based on the user-entered date range and animal       |
|                                              |             |                                                                        |
| **animals**                                  |             |                                                                        |
| `/api/v1/animals/{id}`                       | GET         | Get the animal with the specified ID                                    |
| `/api/v1/animals/{id}`                       | PUT         | Update the animal with the specified ID                                |
| `/api/v1/animals/{id}`                       | DELETE      | Delete the animal with the specified ID                                |
| `/api/v1/animals`                            | GET         | Get all animals                                                        |
| `/api/v1/animals`                            | POST        | Add a new animal                                                       |
| `/api/v1/animals/byName`                     | GET         | Get animals by name                                                    |
| `/api/v1/animals/byCustomerName`             | GET         | Get animals by pet owners                                              |
|                                              |             |                                                                        |

