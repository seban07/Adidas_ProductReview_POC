# Adidas_ProductReview_Poc

Adidas ProductReview is a simple spring boot application, which enables authenticated users to post review for their favourite products, and view the average review score for selected products.

Architecture
*************

1. Java 15.0.2
2. Maven (wrapper)

Microservices
--------------
  1. AdidasUserAuthenticationServise - To create new user, and to login(validate user - returns JWT token, required for AdidasReviewService)
  2. AdidasReviewService - Enables authenticated users(Users who have logged in to AdidasUserAuthenticationServise and recieved JWT token) to post, update, delete    and view reviews for products
  3. AdidasProductService - It's an open source service, available for any user, to view the average review score for any selected product.

Database
---------
  1. AdidasReviewDB - MongoDb
    > Stores review related data (add review/update review/delete review)
  2. adidasuserDB - MongoDb
    > Stores new user info (userid / password)

Requirements
*************

1. MongoDb (Ran out of time, by tomorrow, I will have docker containarized all microservices and MongoDb will be able to run from docker)
2. Java 11 (to run locally in IDE)

Setup
******

The applications are configured, thus there is no need for further configuration.
At startup, database seed data is inserted into DB if no documents are present in Review collection.

Startup
********

1. Git clone these 3 microservices to your local system. Import these maven applications to your local IDE (STS, Eclipse)
2. Run AdidasUserAuthenticationServise first. Create a new user by sending the postman attached request, and the login with the created user. On successfull login, a JWT token will be recieved.
3. Run AdidasReviewService second. Perform CRUD operations created for this service. You need to pass the JWT token recieved from user authentication service, as bearer token in all operations in AdidasReviewService inorder to avaoid any tampering of data in DB by unauthorized users.
4. Run AdidasProductService. Its an open source application and thus any user can perform the only Get operation and view the review scores of a product.

5. Docker containerization of these applications in progress, once done, will upload the docker-compose.yml file. (Above steps can be ignored once dockerized)
You can simply navigate to the folder containing the docker-compose.yml file and run the following commands:
$ docker-compose build && docker-compose up

API Testing
************
Postman API requests/tests are available in this folder

Swagger Docs
*************
Swagger documentation available at /swagger-ui.html for both services.

1. AdidasReviewService : http://localhost:9091/swagger-ui.html
2. AdidasProductService : http://localhost:9092/swagger-ui.html

Possible Future Improvements / Features
****************************************

Dockerize the entire application (coming soon.)
Enhanced security using strict networking in Docker setup.
Enhanced Service Discovery through technologies such as Eureka.
Enhanced Routing and pre-filter using Netflix-Zuul.
Enhance services to cope with high volume and latency by making use of messaging queues like Kafka/Active MQ.
Self management through technologies such as Kubernetes.
Improve integration and unit tests with broader coverage.
Enhance code quality by monitoring code in Sonarqube.
