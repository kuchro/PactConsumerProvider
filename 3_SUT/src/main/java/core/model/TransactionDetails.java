package core.model;

public class TransactionDetails {

    private Long id;
    private Transaction transaction;
    private Status status;

    public TransactionDetails() {
    }

    public TransactionDetails(Long id, Transaction transaction, Status status) {
        this.id = id;
        this.transaction = transaction;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
