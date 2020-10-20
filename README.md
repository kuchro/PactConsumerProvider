* Run ./gradlew 3_SUT:build jar
* Run docker-compose up -d --force-recreate
* Pact Broker will be available - http://localhost:4699
* Test Rest API will be available - http://localhost:4698/v1/provider/transaction/1
* Execute auto-tests from 1_ContractConsumer and 2_ContractProvider