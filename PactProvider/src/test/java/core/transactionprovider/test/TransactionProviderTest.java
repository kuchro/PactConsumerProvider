package core.transactionprovider.test;


import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import core.SpringApp;
import core.model.Status;
import core.model.Transaction;
import core.service.TransactionService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(classes = SpringApp.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@PactFolder("..\\pacts")
@PactBroker(host = "localhost",port = "80")
@Provider("transaction_provider")
public class TransactionProviderTest {

    @MockBean
    TransactionService transactionService;

    @TestTarget
    public final Target target = new HttpTarget(8080);

    @State(value = "execute transaction")
    public void executeTransaction(){
        Status status = new Status("TRANSACTION_FAILURE","Transaction received.");
        when(transactionService.executeTransaction(any(Transaction.class))).thenReturn(status);
    }

}
