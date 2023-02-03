FROM alpine:3.17
RUN apk add --no-cache openjdk17-jre-headless
ENV JAVA_HOME=/opt/openjdk-17
ENV PATH=/opt/openjdk-17/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin.
ENV JAVA_VERSION=17.0.6_p10-r0
ENV DB_USERNAME=user
ENV DB_PASSWORD=password
ENV DB_HOST=user-db
ENV POSTGRES_DB_NAME=userdb
EXPOSE 8080
ADD target/user-service-1.jar user-service-1.jar
ENTRYPOINT ["java", "-jar", "/user-service-1.jar","--DB_USERNAME=user --DB_PASSWORD=password --USER_SERVICE_URL=http://user-service:8080/users/ --DB_URL=jdbc:postgresql://user-db:5432/userdb"]