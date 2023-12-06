FROM amazoncorretto:17
ARG SPRING_PROFILE
ARG SERVICE_NAME=hanghae-plus-3
ENV SPRING_PROFILE_ACTIVE=${SPRING_PROFILE}

RUN mkdir deploy
COPY build/libs/*.jar /${SERVICE_NAME}.jar
RUN chmod +x /${SERVICE_NAME}.jar
COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILE_ACTIVE}", "-jar", "/hanghae-plus-3.jar"]