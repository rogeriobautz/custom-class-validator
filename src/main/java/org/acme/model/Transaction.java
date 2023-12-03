package org.acme.model;

import org.acme.validator.BrokerNoteIdRequired;
import org.acme.validator.EnumValue;

@BrokerNoteIdRequired(transactionType={"COMPRA","VENDA"})
public class Transaction {
    private String brokerNoteId;

    @EnumValue(enumClass=EnumTransactionType.class)
    private String transactionType;

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
