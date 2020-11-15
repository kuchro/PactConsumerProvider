package core.service;

import core.model.Status;
import core.model.Transaction;
import core.model.TransactionDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TransactionService {

    private AtomicLong atomicLong = new AtomicLong(1);

    private List<TransactionDetails> trxHistory = new ArrayList<>();

    public Status executeTransaction(Transaction transaction){
        long id = atomicLong.getAndIncrement();
        transaction.setTransactionId(id);
        Status status = new Status("TRANSACTION_SUCCESS","Transaction received.");
        trxHistory.add(new TransactionDetails(id,transaction,status));
        return status;
    }

    public Transaction getTransaction(Long transactionId) {
        return trxHistory.stream().filter(trx-> trx.getTransaction().getTransactionId().equals(transactionId))
                .findFirst().map(TransactionDetails::getTransaction).orElse(null);
    }

    public List<TransactionDetails> getTransactionHistory(){
        return trxHistory;
    }
}
