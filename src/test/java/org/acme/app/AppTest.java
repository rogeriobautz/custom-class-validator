package org.acme.app;

import java.util.Set;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;

import io.quarkus.test.junit.QuarkusTest;

import org.acme.model.Transaction;
import org.acme.service.ValidateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class AppTest {

    @Inject
    ValidateService validateService;

    Transaction transaction;

    @BeforeEach
    void setup() {
        transaction = new Transaction();
    }

    @Test
    void shouldValidate() {
        // Transaction transaction=new Transaction();
        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319252233");
        transaction.setTransactionType("COMPRA");
        Set<ConstraintViolation<Transaction>> errors = validateService.validateTransaction(transaction);
        Assertions.assertEquals(0, errors.size());

        transaction.setBrokerNoteId(null);
        transaction.setTransactionType("DIVIDENDOS");
        errors = validateService.validateTransaction(transaction);
        Assertions.assertEquals(0, errors.size());
    }

    @Test
    void shouldNotValidate() {
        // Transaction transaction=new Transaction();
        transaction.setBrokerNoteId(null);
        transaction.setTransactionType("COMPRA");
        Set<ConstraintViolation<Transaction>> errors = validateService.validateTransaction(transaction);
        Assertions.assertTrue(errors.size() > 0);

        transaction.setBrokerNoteId("");
        transaction.setTransactionType("VENDA");
        errors = validateService.validateTransaction(transaction);
        Assertions.assertTrue(errors.size() > 0);

        transaction.setBrokerNoteId(
                "d67dcff7-5832-401d-9782-e24d19f9bb8a-d67dcff7-5832-401d-9782-e24d19f9bb8a-d67dcff7-5832-401d" +
                        "-9782-e24d19f9bb8a-d67dcff7-5832-401d-9782-e24d19f9bb8a-d67dcff7-5832-401d-9782-e24d19f9bb8a");
        transaction.setTransactionType("VENDA");
        errors = validateService.validateTransaction(transaction);
        Assertions.assertTrue(errors.size() > 0);

        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319252233");
        transaction.setTransactionType("EMPRESTIMO");
        errors = validateService.validateTransaction(transaction);
        Assertions.assertTrue(errors.size() > 0);

    }

}
