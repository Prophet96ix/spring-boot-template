FROM openjdk:17
COPY . /app/

EXPOSE 8080

WORKDIR /app
RUN ./gradlew bootJar
CMD java -jar build/libs/*.jar
