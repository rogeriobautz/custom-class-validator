package org.acme.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.acme.model.Transaction;

public class BrokerNoteIdRequiredValidator implements ConstraintValidator<BrokerNoteIdRequired, Transaction> {

    String[] transactionType;

    @Override
    public void initialize(BrokerNoteIdRequired constraintAnnotation) {
        this.transactionType = constraintAnnotation.transactionType();
    }

    @Override
    public boolean isValid(Transaction transaction, ConstraintValidatorContext constraintContext) {

        if (transaction.getBrokerNoteId() == null || transaction.getBrokerNoteId().trim().isEmpty()) {
            for (String type : transactionType) {
                if (transaction.getTransactionType().equals(type))
                    return false;
            }
            return true;
        }

        if(!transaction.getBrokerNoteId().matches("^[a-zA-Z0-9][a-zA-Z0-9-]{0,99}$") || transaction.getBrokerNoteId().length() > 100){
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("Broker Note Id format is wrong").addConstraintViolation();
            return false;
        }

        return true;
    }
}