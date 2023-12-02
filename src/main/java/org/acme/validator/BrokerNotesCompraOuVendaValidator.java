package org.acme.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.acme.model.Transaction;

public class BrokerNotesCompraOuVendaValidator implements ConstraintValidator<BrokerNotesCompraOuVenda, Transaction> {

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintContext) {
        return !((transaction.getBrokerNotes() == null || transaction.getBrokerNotes().trim().isEmpty())
                && (transaction.getTransactionType() == "COMPRA" || transaction.getTransactionType() == "VENDA")); 

    }
}