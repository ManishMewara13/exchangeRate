# Exchange Rate Spring Boot Application
https://docs.google.com/document/d/1wir2KEYIZq2Gn-GY3CF9Ree8ge1l56dwS8K5QGqsouU/edit?usp=sharing

> Description

This Spring Boot application is designed to manage exchange rates, allowing users to retrieve, create, update, and delete exchange rate data. The application also includes functionality to calculate and display the difference in exchange rates over various time intervals.


> Prerequisites

Java Version : 11 / 11+

Gradle

MongoDB

IDE : IntellijIDEA(Preferable)


> Installation

Clone the project repository from GitHub:

git clone https://github.com/ManishMewara13/exchangeRate.git

cd project-directory

./gradlew clean build


> Configuration

- Set up MongoDB:
  
Install MongoDB locally if not already installed.

Create a database named 'exchangeRateDb'.

Collection Name: exchangeRateCollection

- Fields:

_id: Unique identifier for each document.

currencyCode: Currency code.

rate: Exchange rate value.

date: Date of the exchange rate.

- Indexes:

Create a compound index on the currencyCode and date fields for efficient querying:

db.exchangeRateCollection.createIndex({ "currencyCode": 1, "date": 1 })



