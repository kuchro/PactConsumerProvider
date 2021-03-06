package transactionprovider.test;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
//@PactFolder("..\\pacts")
@PactBroker(host = "localhost",port = "88")
@Provider("transaction_provider")
public class TransactionProviderTest {



    @TestTarget
    public final Target target = new HttpTarget("localhost",8081);

    @State(value = "execute trx")
    public void executeTransaction(){

    }
    @State(value = "get transaction")
    public void getTransaction(){

    }



}
