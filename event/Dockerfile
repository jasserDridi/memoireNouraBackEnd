

## Stage 2 : create the docker final image
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.4
WORKDIR /work/
COPY  ./target/event-dev.jar /work/application

EXPOSE 8080

CMD ["./application"]
