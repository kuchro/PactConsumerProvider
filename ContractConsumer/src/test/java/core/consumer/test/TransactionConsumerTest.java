package core.consumer.test;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.jayway.jsonpath.JsonPath;
import core.Application;
import model.Transaction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TransactionConsumerTest {

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("transaction_provider",
                                                            "127.0.0.1",8081, this);

    private RestTemplate restTemplate = new RestTemplate();

    @Pact(provider="transaction_provider",consumer="transaction_consumer_post")
    public RequestResponsePact createTransactionPact(PactDslWithProvider builder){
        System.setProperty("pact.rootDir","..\\pacts");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", MediaType.APPLICATION_JSON_VALUE);


        PactDslJsonBody bodyRequest = new PactDslJsonBody()
                .stringValue("transactionId","12345")
                .stringValue("receiver","XSD1234")
                .stringValue("sender","XSD3214")
                .integerType("amount",34560)
                .stringValue("currency","EUR");
        PactDslJsonBody bodyResponse = new PactDslJsonBody()
                .stringValue("status","TRANSACTION_SUCCESS")
                .stringValue("message","Transaction received.");

        return builder.given("execute transaction")
                .uponReceiving("a example of transaction")
                .path("/v1/provider/transaction")
                .body(bodyRequest)
                .headers(headers)
                .method(RequestMethod.POST.name())
                .willRespondWith()
                .headers(headers)
                .status(200)
                .body(bodyResponse).toPact();
    }


    @Test
    @PactVerification
    public void testCreateTransactionConsumer(){
        Transaction transaction = new Transaction("12345","XSD1234","XSD3214",
                                                      34560,"EUR");
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request=new HttpEntity<Object>(transaction, headers);
        System.out.println("Provider URL"+mockProvider.getUrl());
        ResponseEntity<String> responseEntity=
                restTemplate.postForEntity(mockProvider.getUrl()+"/v1/provider/transaction", request, String.class);
        assertEquals("TRANSACTION_SUCCESS", JsonPath.read(responseEntity.getBody(),"$.status"));
        assertEquals("Transaction received.", JsonPath.read(responseEntity.getBody(),"$.message"));


    }


 }
