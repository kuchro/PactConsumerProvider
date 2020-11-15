FROM openjdk:14-alpine

ENV HOME /app/source

RUN mkdir -p $HOME

COPY ./3_SUT/build/libs/3_SUT.jar $HOME/provider.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/source/provider.jar"]