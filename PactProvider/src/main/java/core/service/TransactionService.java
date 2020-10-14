package core.service;

import core.model.Status;
import core.model.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    public Status executeTransaction(Transaction transaction){
        return new Status("","");
    }
}
