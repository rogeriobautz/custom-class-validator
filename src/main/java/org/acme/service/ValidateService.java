package org.acme.service;

import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;

import org.acme.model.ListTransaction;
import org.acme.model.Transaction;
import org.acme.response.ResponseListTransaction;
import org.acme.response.ResponseTransaction;

@ApplicationScoped
public class ValidateService {
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public ValidateService(){}

    public ResponseTransaction validateTransaction(Transaction transaction){
        var transactionValidator=this.validatorFactory.getValidator();
        var response = new ResponseTransaction();
        response.setValid(true);
        if(transactionValidator.validate(transaction).size() > 0){
            response.setValid(false);
            response.setInvalidTransaction(transaction);
        }
        return response;         
    }  

    public ResponseListTransaction validateListAndEveryTransaction(ListTransaction listTransaction){
        var transactionValidator=validatorFactory.getValidator();
        var response = new ResponseListTransaction();
        response.setValid(true);
        for(var transaction:listTransaction.getResources())
        {
            if(transactionValidator.validate(transaction).size() > 0){
                response.setValid(false);
                response.setMessage("Erro em uma ou mais transações");
                response.addInvalidTransaction(transaction);
            }
        }
        if(this.validateListTransaction(listTransaction).size() > 0){
            var message = "Erro nos campos da Lista";
            if(response.getMessage()==null){
                response.setMessage(message);
            }
            else{
                response.setMessage(message + " e " + response.getMessage());
            }
        }
        return response;         
    }       
    
    
    public Set<ConstraintViolation<ListTransaction>> validateListTransaction(ListTransaction listTransaction){
        var listTransactionValidator=validatorFactory.getValidator();
        var violations= listTransactionValidator.validate(listTransaction);
        return violations;         
    }
}


