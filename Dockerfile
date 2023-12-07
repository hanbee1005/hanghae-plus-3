FROM amazoncorretto:17
ARG SPRING_PROFILE
ARG APP_VERSION=0.0.1
ARG SERVICE_NAME=hanghae-plus-3
ENV SPRING_PROFILE_ACTIVE=${SPRING_PROFILE}

RUN mkdir deploy
COPY build/libs/${SERVICE_NAME}-${APP_VERSION}.jar /app.jar
RUN chmod +x /app.jar
COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE_ACTIVE}", "-jar", "/app.jar"]