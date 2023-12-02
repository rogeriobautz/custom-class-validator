package org.acme.model;

import jakarta.validation.constraints.NotBlank;

import org.acme.validator.BrokerNotesCompraOuVenda;

@BrokerNotesCompraOuVenda
public class Transaction {
    private String brokerNotes;

    @NotBlank
    private String transactionType;

    public String getBrokerNotes() {
        return brokerNotes;
    }
    public void setBrokerNotes(String brokerNotes) {
        this.brokerNotes = brokerNotes;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    
}
