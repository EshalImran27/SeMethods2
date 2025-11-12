FROM eclipse-temurin:21-jdk
COPY ./target/sem2.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "sem2.jar", "db:3306", "30000"]