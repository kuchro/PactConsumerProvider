FROM openjdk:13.0.2
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ./gradlew PactProvider:build jar
COPY /app/source/PactProvider/build/libs/PactProvider.jar /app/provider.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/provider.jar"]

