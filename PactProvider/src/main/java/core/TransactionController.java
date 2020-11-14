package core;

import core.model.Status;
import core.model.Transaction;
import core.model.TransactionDetails;
import core.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/provider/transaction/history")
    public List<TransactionDetails> getTransactionHistory(){
        return transactionService.getTransactionHistory();
    }

    @PostMapping("/provider/transaction")
    public Status executeTransaction(@RequestBody @Valid Transaction transaction){
        return transactionService.executeTransaction(transaction);
    }

    @GetMapping("/provider/transaction/{transactionId}")
    public Transaction provideTransaction(@PathVariable Long transactionId){
        return Optional.ofNullable(transactionService.getTransaction(transactionId))
                .orElseThrow(()->  new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Transaction not found"));
    }
}
