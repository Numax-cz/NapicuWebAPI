FROM maven:3.6.3-openjdk-15

EXPOSE 80

RUN mvn spring-boot:build-image -f pom.xml

ADD target/*.jar /app/app.jar



RUN cd /app

WORKDIR /app

ENTRYPOINT ["java", "-jar", "./app.jar", "--server.port=80", "--spring.profiles.active=prod"]
