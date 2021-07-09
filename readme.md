# Bank Customer Data APIs

A simple REST API built with Spring boot to manage customer data for a bank


## Pre-requisites

- JDK 8
- Spring Boot 2.4.8
- Gradle 7.1

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.4.8.RELEASE)


## Summary

This app provides services to manage bank customers using REST APIs. Spring Boot is the main framework with an in-memory database (H2). The raw datasets of customer account/bank branch/transaction are included in this project. Spring Boot JPA allows this app to access and persist data between account/bank branch/transaction entity and relational databases. It is also utilized to provide a powerful repository and custom object-mapping abstraction. Lastly, the implemented REST APIs for users to analyze a variety of datasets for better decision making

## Installation

##### 1. Clone the application

```
$ git clone https://github.com/doyunbk/bank-spring-boot.git
$ cd Bank-Customer-Data-APIs
```

##### 2. Build and run the app using Gradle
```
$ gradle build
$ gradle bootRun
```

##### 3. Use REST APIs via CRUD operation

- Find a customer who has the highest amount of transactions per year

```
$ curl --location --request GET 'localhost:8080/test/largest-amt-transaction'
```

- Find customers who do not have any transactions per year
```
$ curl --location --request GET 'localhost:8080/test/missing-accounts'
```

- Get a list of the bank branches by the total amount of transactions per year
```
$ curl --location --request GET 'localhost:8080/test/transaction-amt-by-branch'
```

- Get a specific bank branch with the total amount of transactions by searching a name
```
$ curl --location --request GET 'localhost:8080/test/transaction-by-branch?branchName={branch_name}'
```

##### 4. Testing
```
$ gradle test
```


