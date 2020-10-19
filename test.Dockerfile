FROM openjdk:13.0.2
RUN mkdir -p /tests/source
COPY . /tests/source
WORKDIR /tests/source
RUN ./gradlew TestsVerification:test