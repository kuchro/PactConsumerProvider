package core.service;

import core.model.Status;
import core.model.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class TransactionService {

    private List<Transaction> transactions = List.of(
            new Transaction("1","Bank A","Bank B",20,"PLN"),
            new Transaction("2","Bank B","Bank A",25,"PLN")
            );
    public Status executeTransaction(Transaction transaction){
        return new Status("TRANSACTION_SUCCESS","Transaction received.");
    }

    public Transaction getTransaction(String transactionId) {
        return transactions.stream().filter(trx-> trx.getTransactionId().equals(transactionId))
                .findFirst().orElse(null);
    }
}
