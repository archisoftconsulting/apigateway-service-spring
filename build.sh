cd apigateway-service
mvn package docker:build
cd ../oauth2-service
mvn package docker:build
cd ../play-service
./sbt docker:publishLocal
