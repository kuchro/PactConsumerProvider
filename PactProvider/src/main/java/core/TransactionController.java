package core;

import core.model.Status;
import core.model.Transaction;
import core.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;


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

    @GetMapping("/provider/transaction/{transactionId}")
    public Optional<Transaction> provideTransaction(@PathVariable String transactionId){
        return Optional.ofNullable(Optional.ofNullable(transactionService.getTransaction(transactionId))
                .orElseThrow(()->  new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Transaction not found")));
    }
}
