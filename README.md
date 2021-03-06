# Apigateway in SpringBoot

This is a starter application for apigateway. The purpose of the API gateway is to handle external traffic.
Services should communicate directly without a gateway/ESB-style mediator.

Functionalities in this apigateway:

1. Routing
2. Rate Limiting
3. Authentication
4. Authorization
5. Role Based Access Control(RBAC)

## Download the project
git clone this project or download the zip file https://github.com/archisoftconsulting/apigateway-service-spring/archive/master.zip
Extract into any folder
Open Terminal command


## Running

```
./build.sh

sudo docker-compose up -d
```
## Check status
```
sudo docker ps
```
## Shutdown
```
sudo docker-compose down
```

Once the application is up, go to 


1. http://localhost:8080 to see the apigateway home page
2. http://localhost:9000 to see the playframework java rest service.
3. http://localhost:8080/public to test the routing to rest service. Refresh more than 5 times to test rate limit.
4. http://localhost:8080/protected. to test the protected rest service. 

## User, role and Permission control

| Login id      | Role          | Permission Control  | HTTP Method |
| ------------- | ------------- |---------------------|-------------|
| reader        | reader        | read-only           | HTTP-GET    |
| writer        | writer        | write-only          | HTTP-POST   |
| admin         | admin         | read and write      | HTTP-GET and HTTP-POST|

password=password

## Service ports

```
sudo docker ps
```

1. APIGATEWAY Service = 8080
2. OAUTH Service = 9999
3. Play REST Service = 9000
4. Redis database = 6379

## References

1. In browser enter: 
```
http://localhost:8080/protected
```
(It throw error unauthorize)
 
```
http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=b2b&redirect_uri=http://localhost:8080/protected
```
(It generate code=xxx,http://localhost:8080/protected?code=4qpeXa)

2. In command line, enter the below with the new code generated
```
curl b2b:b2bsecret@localhost:9999/uaa/oauth/token  \
-d grant_type=authorization_code -d client_id=b2b     \
-d redirect_uri=http://localhost:8080/protected -d code=4qpeXa
```
(Access token generated)

3. In command line, enter the below with the accesstoken generated
```
curl -H "Authorization: Bearer [AccessToken]" localhost:8080/protected
```

## Setup docker/docker compose

Refer :  
https://github.com/archisoftconsulting/vagrantbox-starter

Email:  
khimsuan.tan@archisoftconsulting.com  
cheeken.wong@archisoftconsulting.com
