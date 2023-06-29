# Stage 1: Create the Docker final image
FROM ghcr.io/graalvm/graalvm-ce:java11-21.2.0 as graalvm
WORKDIR /app
COPY target/event-1.0.0-SNAPSHOT-native-image-source-jar/event-1.0.0-SNAPSHOT-runner.jar /app/application.jar
COPY target/event-1.0.0-SNAPSHOT-native-image-source-jar/lib/* /app/lib/
EXPOSE 8099
CMD ["java", "-jar", "-Djava.library.path=/app/lib", "/app/application.jar"]
