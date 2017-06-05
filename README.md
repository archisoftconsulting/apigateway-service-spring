# Apigateway in SpringBoot

This is a starter application for apigateway. The purpose of the API gateway is to handle external traffic.
Services should communicate directly without a gateway/ESB-style mediator.

## Download the project
gitclone this project or download the zip file https://github.com/archisoftconsulting/apigateway-service-spring/archive/master.zip
Extract into any folder
Open Terminal command


## Running

```
cd apigateway-service
mvn spring-boot:run
cd oauth2-service
mvn spring-boot:run
cd greeting-service
mvn spring-boot:run
```

Once the application is up, go to 

1. http://localhost:8080 to see the apigateway home page
2. http://localhost:8081 to see the greeting service.
3. http://localhost:8080/public to test the routing
4. http://localhost:8080/protected to test the protected resource.
5. http://localhost:8080/readonly to test the readonly resource, you can GET but cant do POST.
