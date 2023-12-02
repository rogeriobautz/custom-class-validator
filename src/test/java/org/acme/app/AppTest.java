package org.acme.app;

import java.util.Set;

import jakarta.validation.ConstraintViolation;

import org.acme.model.Transaction;
import org.acme.service.ValidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {     
    
        
     @Test
    void shouldValidate() {
        Transaction transaction=new Transaction();
        transaction.setBrokerNotes("0123456789012345");
        transaction.setTransactionType("COMPRA"); 
        ValidateService validateService=new ValidateService();
        Set<ConstraintViolation<Transaction>> errors= validateService.validateTransaction(transaction);
        Assertions.assertEquals(0,errors.size());
    }

    @Test
    void shouldNotValidate() {
        Transaction transaction=new Transaction();
        transaction.setBrokerNotes("");
        transaction.setTransactionType("COMPRA"); 
        ValidateService validateService=new ValidateService();
        Set<ConstraintViolation<Transaction>> errors= validateService.validateTransaction(transaction);
        Assertions.assertNotEquals(0,errors.size());
    }
}
