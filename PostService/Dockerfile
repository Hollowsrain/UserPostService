FROM alpine:3.17
RUN apk add --no-cache openjdk17-jre-headless
ENV JAVA_HOME=/opt/openjdk-17
ENV PATH=/opt/openjdk-17/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin.
ENV JAVA_VERSION=17.0.6_p10-r0
EXPOSE 8081
ADD target/post-service-latest.jar post-service-latest.jar
ENTRYPOINT ["java", "-jar", "/post-service-latest.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]

