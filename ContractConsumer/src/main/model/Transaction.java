package model;



public class Transaction {
    private Long transactionId;
    private String receiver;
    private String sender;
    private Integer amount;
    private String currency;



    public Transaction(Long transactionId, String receiver, String sender, int amount, String currency) {
        this.transactionId=transactionId;
        this.receiver = receiver;
        this.sender = sender;
        this.amount = amount;
        this.currency = currency;
    }


    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
