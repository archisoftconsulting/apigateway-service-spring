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
5. http://localhost:8080/readonly to test the readonly resource, you can GET but cant do POST. Test this using postman


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

curl -H "Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0OTY2NzYxODIsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FDVFVBVE9SIl0sImp0aSI6IjMxZWU3YmNkLWE3N2MtNGU2OS05ZWNjLTBlMmJhYzEwOWJlZCIsImNsaWVudF9pZCI6ImIyYiIsInNjb3BlIjpbInJlYWQiXX0.btAm_ZhWR5hdMALqR1XRGkyKMaHC89ZENzmYJ5nHFofAn8WsZI9jqrucA9E34-NgcR0mHfGH4fgkh9q5MFojyju1_2GkfCh9w4Ee5WaW_hKNgahP9m5-luUTvdsILAkhF-jaWgpKaTub9UC_oBoiQ9XpFKikqoTwV3D3ls9Xp7OWvm2gJBo2x10Np-_jhVy_cOsxV75hoMK6MEvHeEM8MJX5qWe6hBfvSNnYwtbIA9FVpJQeLjh-IcuwGIgVl0LSIagsIQkt_1FDzLbGnY-ESDirVjHgQhfp4UmCcpXz18gJEj7TfD53C-WzQAR2LX-gQHie8u0yLtwMJoH7gEeP3Q" localhost:8080/protected

