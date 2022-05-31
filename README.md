# flashsale
This repository is used to share the flash sale online assignment

1. Checkout the repository from git.
2. Following prerequisites are required to run the appication on your local:
   # Java 17 - https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html
   # PostgreSQL - https://www.postgresql.org/download/
   # RabbitMQ - https://www.rabbitmq.com/download.html
   # Eclipse - https://www.eclipse.org/downloads/packages/
 
3. Please make sure all the above sofwares are installed and PostgreSQL and RabbitMQ(http://localhost:15672/) is up and running.
4. Import the maven project into Eclipse.
5. The postgreSQL database and RabbitMQ credentials are in application.properties. Please feel free to update them depending on your local setup.
6. Right click on FlashSaleApplication.java file and run as Java Application
7. In order to Test the API's using Swagger UI open the following link in the browser - http://localhost:8080/swagger-ui/index.html
8. In order to run the Integration tests navigate to src/test/java folder and right click on the FlashSaleUserControllerTest.java file and run as Junit Test. 
   Verify the test results. Repeat the same step for Integration Testing of other API's.
