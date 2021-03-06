FROM openjdk:8

COPY . /usr/src/bookstore-spring-boot
WORKDIR /usr/src/bookstore-spring-boot
CMD chmod +x .gradlew
RUN ./gradlew build
RUN ls build/libs
COPY build/libs/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
