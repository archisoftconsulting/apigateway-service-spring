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

sudo docker-compose up -d
(install and up redis database)
```

Once the application is up, go to 


1. http://localhost:8080 to see the apigateway home page
2. http://localhost:9000 to see the play rest service.
3. http://localhost:8080/public to test the routing to rest service
4. http://localhost:8080/protected. to test the protected rest service. 

a. User with role=writer can POST but cannot GET

b. User with role=reader can GET but cannot POST

c. User with role=writer,reader can POST and GET

## User role and permission control

Login id=reader (only can GET but cannot POST)

Login id=writer (only can POST but cannot GET)

Login id=admin  (can POST and GET)

password=password

## Server PORTs

1. APIGATEWAY Server = 8080
2. OAUTH Server = 9999
3. REST API = 8081

## References

protected rest
http://localhost:8080/protected

upon successful login, generate request token
http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=b2b&redirect_uri=http://localhost:8080/protected

request token generated
http://localhost:8080/protected?code=4qpeXa

generated access token
curl b2b:b2bsecret@localhost:9999/uaa/oauth/token  \
-d grant_type=authorization_code -d client_id=b2b     \
-d redirect_uri=http://localhost:8080/protected -d code=G88QC5

try to access the page/service with token

curl -H "Authorization: Bearer [jwttokenstringxxxxx]" localhost:8080/protected

