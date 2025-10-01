FROM openjdk:latest
COPY ./target/SeMethods2-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SeMethods2-1.0-SNAPSHOT-jar-with-dependencies.jar"]