FROM openjdk:11
MAINTAINER example.com
ADD target/sumApplication-0.0.1-SNAPSHOT.jar sum-application
EXPOSE 80
ENTRYPOINT ["java", "-jar", "sum-application"]