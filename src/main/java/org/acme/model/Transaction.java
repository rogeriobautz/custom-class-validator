package org.acme.model;

import org.acme.enums.EnumTransactionType;
import org.acme.validator.BrokerNoteIdRequired;
import org.acme.validator.EnumValue;

import jakarta.validation.constraints.NotEmpty;

@BrokerNoteIdRequired(transactionType={"COMPRA","VENDA"})
public class Transaction {
    private String brokerNoteId;

    @EnumValue(enumClass=EnumTransactionType.class)
    private String transactionType;

    @NotEmpty
    private String transactionName;

    public String getTransactionName() {
        return transactionName;
    }
    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }
    public String getBrokerNoteId() {
        return brokerNoteId;
    }
    public void setBrokerNoteId(String brokerNotes) {
        this.brokerNoteId = brokerNotes;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    
}
