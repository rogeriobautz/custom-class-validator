package org.acme.service;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.acme.model.Transaction;

public class ValidateService {

    public Set<ConstraintViolation<Transaction>> validateTransaction(Transaction transaction){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator=validatorFactory.getValidator();
        Set<ConstraintViolation<Transaction>> violations= validator.validate(transaction);
        return violations; 
        
    }    
    
}


