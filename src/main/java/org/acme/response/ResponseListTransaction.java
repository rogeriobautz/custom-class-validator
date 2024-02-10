package org.acme.response;

import java.util.ArrayList;
import java.util.List;

import org.acme.model.Transaction;

public class ResponseListTransaction {
    private boolean valid;
    private String message;
    private List<Transaction> invalidTransactions = new ArrayList<>();

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    public List<Transaction> getInvalidTransactions() {
        return invalidTransactions;
    }
    public void addInvalidTransaction(Transaction transaction) {
        this.invalidTransactions.add(transaction);
    }
}
