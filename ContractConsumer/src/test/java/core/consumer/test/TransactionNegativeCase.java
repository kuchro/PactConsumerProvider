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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

public class TransactionNegativeCase {

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("transaction_negative",
            "127.0.0.1",8081, this);

    private RestTemplate restTemplate = new RestTemplate();

    @Pact(provider="transaction_negative",consumer="transaction_consumer_get_404")
    public RequestResponsePact createTransactionPactForGet(PactDslWithProvider builder){
        System.setProperty("pact.rootDir","..\\pacts");


        DslPart bodyResponse = new PactDslJsonBody()
                .integerType("status",404)
                .stringValue("message","")
                .stringValue("error","There is no such a element")
                .stringMatcher("date","\\d{4}-\\d{2}-\\d{2}","2020-11-13");

        return builder.given("get trasnaction which not exist")
                .uponReceiving("a example of get transaction")
                .path("/v1/provider/transaction/99")
                .method(RequestMethod.GET.name())
                .willRespondWith()
                .status(404)
                .body(bodyResponse).toPact();
    }

    @Test
    @PactVerification
    public void testGETTransactionConsumer() {

        try{
            System.out.println("Provider URL"+mockProvider.getUrl());
            restTemplate.getForEntity(mockProvider.getUrl()+"/v1/provider/transaction/99", String.class);
        }catch(HttpClientErrorException.NotFound ex){
            assertEquals("There is no such a element", JsonPath.read(ex.getResponseBodyAsString(),"$.error"));
            assertEquals(404, ex.getStatusCode().value());
        }

    }
}
