package core.consumer.test;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.jayway.jsonpath.JsonPath;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class TransactionConsumerGetTrxTest{

    //as a mock server we can use also WireMock
    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("transaction_provider",
            "127.0.0.1",4698, this);

    private RestTemplate restTemplate = new RestTemplate();

    @Pact(provider="transaction_provider",consumer="transaction_consumer_get")
    public RequestResponsePact createTransactionPactForGet(PactDslWithProvider builder){
        System.setProperty("pact.rootDir","..\\pacts");


        DslPart bodyResponse = new PactDslJsonBody()
                .integerType("transactionId",1)
                .stringValue("receiver","BLM1235453242")
                .stringValue("sender","NBP12435342")
                .integerType("amount",1)
                .stringValue("currency","PLN");

        return builder.given("get transaction")
                .uponReceiving("a example of get transaction")
                .path("/v1/provider/transaction/1")
                .method(RequestMethod.GET.name())
                .willRespondWith()
                .status(200)
                .body(bodyResponse).given("get transaction2")
                .uponReceiving("a example of get transaction")
                .path("/v1/provider/transaction/null")
                .method(RequestMethod.GET.name())
                .willRespondWith()
                .status(400)
                .body("null").toPact();
    }

    @Test
    @PactVerification
    public void testGETTransactionConsumer(){

        HttpHeaders headers=new HttpHeaders();
        System.out.println("Provider URL"+mockProvider.getUrl());
        ResponseEntity<String> responseEntity=
                restTemplate.getForEntity(mockProvider.getUrl()+"/v1/provider/transaction/1", String.class);
        assertEquals("BLM1235453242", JsonPath.read(responseEntity.getBody(),"$.receiver"));
    }
}
