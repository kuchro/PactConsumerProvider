FROM openjdk:13.0.2 as builder
RUN mkdir -p /app/source
COPY . /app/source
WORKDIR /app/source
RUN ./gradlew ContractConsumer:build jar
RUN ./gradlew PactProvider:build jar

FROM openjdk:13.0.2 as runapp
COPY --from=builder /app/source/PactProvider/build/libs/PactProvider.jar /app/provider.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/provider.jar"]

