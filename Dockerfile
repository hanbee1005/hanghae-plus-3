FROM amazoncorretto:17
ARG SPRING_PROFILE
ARG SERVICE_NAME
ARG APP_VERSION
ENV SPRING_PROFILE_ACTIVE=${SPRING_PROFILE}

RUN mkdir deploy
COPY build/libs/${SERVICE_NAME}-${APP_VERSION}.jar /${SERVICE_NAME}.jar
RUN chmod +x /${SERVICE_NAME}.jar
COPY docker-entrypoint.sh .
RUN chmod +x docker-entrypoint.sh

ENTRYPOINT ["./docker-entrypoint.sh"]