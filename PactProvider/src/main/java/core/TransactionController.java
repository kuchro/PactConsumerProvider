package core;

import core.model.Status;
import core.model.Transaction;
import core.service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/provider/transaction")
    public Status executeTransaction(@RequestBody Transaction transaction){
        return transactionService.executeTransaction(transaction);
    }
}
