FROM openjdk:13.0.2
ENV HOME /app/source

RUN mkdir -p $HOME

COPY ./PactProvider/build/libs/PactProvider.jar $HOME/provider.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/source/provider.jar"]

