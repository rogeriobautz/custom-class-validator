package org.acme.response;

import org.acme.model.Transaction;

public class ResponseTransaction {
    private boolean valid;
    private Transaction invalidTransaction;
    
    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    public Transaction getInvalidTransaction() {
        return invalidTransaction;
    }
    public void setInvalidTransaction(Transaction invalidTransaction) {
        this.invalidTransaction = invalidTransaction;
    }

    
}
