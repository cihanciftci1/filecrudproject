FROM openjdk:11

COPY target/filecrud-0.0.1-SNAPSHOT.jar filecrud.jar

ENTRYPOINT ["java", "-jar", "/filecrud.jar"]