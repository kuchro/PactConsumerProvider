package core.consumer.test;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.core.model.RequestResponsePact;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TransactionConsumerHistoryTest extends ConsumerPactTest {

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
        System.setProperty("pact.rootDir","..\\pacts");
        return builder
                .given("GET Transaction History")
                .uponReceiving("Test to check the trx history.")
                .path("/v1/provider/transaction/history")
                .method(RequestMethod.GET.name())
                .willRespondWith()
                .status(HttpStatus.OK.value())
                .body("[\n" +
                        "    {\n" +
                        "        \"id\": 1,\n" +
                        "        \"transaction\": {\n" +
                        "            \"transactionId\": 1,\n" +
                        "            \"receiver\": \"BLM1235453242\",\n" +
                        "            \"sender\": \"NBP12435342\",\n" +
                        "            \"amount\": 1,\n" +
                        "            \"currency\": \"PLN\"\n" +
                        "        },\n" +
                        "        \"status\": {\n" +
                        "            \"status\": \"TRANSACTION_SUCCESS\",\n" +
                        "            \"message\": \"Transaction received.\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 2,\n" +
                        "        \"transaction\": {\n" +
                        "            \"transactionId\": 2,\n" +
                        "            \"receiver\": \"BLM1235453242\",\n" +
                        "            \"sender\": \"NBP12435342\",\n" +
                        "            \"amount\": 1,\n" +
                        "            \"currency\": \"PLN\"\n" +
                        "        },\n" +
                        "        \"status\": {\n" +
                        "            \"status\": \"TRANSACTION_SUCCESS\",\n" +
                        "            \"message\": \"Transaction received.\"\n" +
                        "        }\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"id\": 3,\n" +
                        "        \"transaction\": {\n" +
                        "            \"transactionId\": 3,\n" +
                        "            \"receiver\": \"BLM1235453242\",\n" +
                        "            \"sender\": \"NBP12435342\",\n" +
                        "            \"amount\": 1,\n" +
                        "            \"currency\": \"PLN\"\n" +
                        "        },\n" +
                        "        \"status\": {\n" +
                        "            \"status\": \"TRANSACTION_SUCCESS\",\n" +
                        "            \"message\": \"Transaction received.\"\n" +
                        "        }\n" +
                        "    }\n" +
                        "]").toPact();
    }

    @Override
    protected String providerName() {
        return "transaction_provider_history";
    }

    @Override
    protected String consumerName() {
        return "consumer_test_trx_details";
    }

    @Override
    protected void runTest(MockServer mockServer, PactTestExecutionContext context) throws IOException {
         RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(mockServer.getUrl()+"/v1/provider/transaction/history", String.class);

        assertEquals(new JSONArray(){{add("BLM1235453242");add("BLM1235453242");add("BLM1235453242");}}, JsonPath.read(responseEntity.getBody(),"$..receiver"));
        JSONArray objMap = JsonPath.read(responseEntity.getBody(),"$");
        assertEquals(3,objMap.size());
    }
}
