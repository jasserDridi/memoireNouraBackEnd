FROM quay.io/quarkus/ubi-quarkus-native-image:21.2.0-java11 AS build
COPY ./pom.xml /code/
COPY ./mvnw /code/mvnw
COPY ./maven-settings.xml /code/maven-settings.xml
COPY ./.mvn /code/.mvn
USER root
WORKDIR /code
RUN chmod +x mvnw
RUN ./mvnw -B org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -s ./maven-settings.xml
COPY ./src /code/src
RUN ./mvnw package -Pnative -Dquarkus.profile=dev -DskipTests=true -Dnative-image.xmx=8g -s ./maven-settings.xml
#RUN  kpm install_java_plugin omciplugin --from-source-file ../killbill-omci-plugin-1.0.1-SNAPSHOT.jar --destination bundles
USER quarkus

## Stage 2 : create the docker final image
FROM registry.access.redhat.com/ubi8/ubi-minimal:8.4
WORKDIR /work/
COPY --from=build /code/target/*-runner /work/application
# set up permissions for user `1001`
RUN chmod 775 /work /work/application \
  && chown -R 1001 /work \
  && chmod -R "g+rwX" /work \
  && chown -R 1001:root /work
EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
