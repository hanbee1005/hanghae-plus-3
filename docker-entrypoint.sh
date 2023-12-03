#!/bin/sh

JAVA_OPTS="${JAVA_OPTS} -Dspring.profiles.active=${SPRING_PROFILE_ACTIVE}"

### app 실행 ###
echo "JAVA_OPTS=${JAVA_OPTS}"
exec java ${JAVA_OPTS} -jar /${SERVICE_NAME}.jar