# FROM docker.artifactory.gcp.mercadona.com/custom/springboot-java:21-jre-alpine-v1
FROM eclipse-temurin:21-jdk

# ARG DEPENDENCY=boot/target/dependency
#
# # Add layers which may be cached
# COPY ${DEPENDENCY}/org ./org
# COPY ${DEPENDENCY}/BOOT-INF/lib ./BOOT-INF/lib
#
# # Add new layers
# COPY ${DEPENDENCY}/META-INF ./META-INF
# COPY ${DEPENDENCY}/BOOT-INF/classes ./BOOT-INF/classes

COPY boot/target/*.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080
