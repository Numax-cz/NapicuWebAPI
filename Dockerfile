FROM maven:3.6.3-openjdk-15

COPY /target/*.jar /app/app.jar

RUN cd /app

WORKDIR /app

CMD java -jar ./app.jar --server.port=80 --spring.profiles.active=prod
