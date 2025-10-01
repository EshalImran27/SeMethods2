FROM openjdk:latest
COPY ./target/SeMethods2-0.1.0.2-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "SeMethods2-0.1.0.2-jar-with-dependencies.jar"]