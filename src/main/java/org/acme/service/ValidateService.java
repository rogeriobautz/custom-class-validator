package org.acme.service;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.acme.model.Transaction;

@ApplicationScoped
public class ValidateService {
    public ValidateService(){}

    public Set<ConstraintViolation<Transaction>> validateTransaction(Transaction transaction){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator=validatorFactory.getValidator();
        Set<ConstraintViolation<Transaction>> violations= validator.validate(transaction);
        return violations; 
        
    }    
    
}


