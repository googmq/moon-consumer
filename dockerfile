FROM openjdk:17.0.2

WORKDIR /data

MAINTAINER d@mail.com

COPY ./target/moon-consumer-1.0.0.jar /data/moon.jar

EXPOSE 8080

CMD ["java","-jar","/data/moon.jar"]