FROM openjdk:11
VOLUME /tmp
COPY target/registory.jar registory.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /registory.jar --debug