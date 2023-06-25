FROM quay.io/quarkus/ubi-quarkus-native-image:21.2.0-java11 AS build
WORKDIR /code
COPY . /code

## Stage 2 : create the docker final image
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.4
WORKDIR /work/
COPY --from=build /code/target/*-runner /work/application
EXPOSE 8080

CMD ["./application"]
