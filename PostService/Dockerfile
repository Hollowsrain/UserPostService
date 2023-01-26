FROM alpine:3.17
RUN apk add --no-cache openjdk17-jre-headless
ENV JAVA_HOME=/opt/openjdk-17
ENV PATH=/opt/openjdk-17/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin.
ENV JAVA_VERSION=17.0.6_p10-r0
ENV DB_USERNAME=post
ENV DB_PASSWORD=password
ENV DB_URL=jdbc:postgresql://post-db:5432/postdb
ENV USER_SERVICE_URL=http://user-service:8080/users/
EXPOSE 8081
ADD target/post-service-latest.jar post-service-latest.jar
ENTRYPOINT ["java", "-jar", "/post-service-latest.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser --DB_USERNAME=post --DB_PASSWORD=password --USER_SERVICE_URL=http://post-service:8080/users/ --DB_URL=jdbc:postgresql://post-db:5433/postdb"]

