FROM maven:3.6.3-openjdk-15

ARG JAR_FILE=*.jar

COPY ${JAR_FILE} /app/app.jar

RUN cd /app

WORKDIR /app

CMD java -jar ./app.jar --server.port=80
