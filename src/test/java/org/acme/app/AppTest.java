package org.acme.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Set;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ConstraintViolation;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;

import org.acme.model.ListTransaction;
import org.acme.model.Transaction;
import org.acme.service.ValidateService;
import org.acme.validator.BrokerNoteIdRequired;
import org.acme.validator.EnumValue;
import org.glassfish.jaxb.runtime.v2.schemagen.xmlschema.List;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

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
    void shouldValidateTransaction() {
        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319252233");
        transaction.setTransactionType("COMPRA");
        transaction.setTransactionName("PIX");
        var response = validateService.validateTransaction(transaction);
        Assertions.assertEquals(true, response.isValid());

        transaction.setBrokerNoteId(null);
        transaction.setTransactionType("DIVIDENDOS");
        response = validateService.validateTransaction(transaction);
        Assertions.assertEquals(true, response.isValid());
    }

    @Test
    void shouldNotValidateTransaction() {
        transaction.setBrokerNoteId(null);
        transaction.setTransactionType("COMPRA");
        transaction.setTransactionName("PIX");
        var response = validateService.validateTransaction(transaction);
        Assertions.assertEquals(false, response.isValid());

        transaction.setBrokerNoteId("");
        transaction.setTransactionType("VENDA");
        response = validateService.validateTransaction(transaction);
        Assertions.assertEquals(false, response.isValid());

        transaction.setBrokerNoteId(
                "d67dcff7-5832-401d-9782-e24d19f9bb8a-d67dcff7-5832-401d-9782-e24d19f9bb8a-d67dcff7-5832-401d" +
                        "-9782-e24d19f9bb8a-d67dcff7-5832-401d-9782-e24d19f9bb8a-d67dcff7-5832-401d-9782-e24d19f9bb8a");
        transaction.setTransactionType("VENDA");
        response = validateService.validateTransaction(transaction);
        Assertions.assertEquals(false, response.isValid());

        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319252233");
        transaction.setTransactionType("EMPRESTIMO");
        response = validateService.validateTransaction(transaction);
        Assertions.assertEquals(false, response.isValid());

    }

    @Test
    void shouldValidateListTransaction() {
        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319252233");
        transaction.setTransactionType("COMPRA");
        transaction.setTransactionName("PIX");
        ListTransaction listTransaction = new ListTransaction();
        listTransaction.setUserName("Usuário de teste");
        listTransaction.addResource(transaction);
        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319255577");
        transaction.setTransactionType("VENDA");
        listTransaction.addResource(transaction);

        for (var tr : listTransaction.getResources()) {
            var response = validateService.validateTransaction(tr);
            Assertions.assertEquals(true, response.isValid());
        }

        Set<ConstraintViolation<ListTransaction>> errors = validateService.validateListTransaction(listTransaction);
        Assertions.assertEquals(0, errors.size());
    }

    @Test
    void shouldNotValidateListTransaction() {
        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319252233");
        transaction.setTransactionType("COMPRA");
        ListTransaction listTransaction = new ListTransaction();
        listTransaction.setUserName("Usuário de teste");
        listTransaction.addResource(transaction);
        transaction.setBrokerNoteId("XWYZ555019929279212650822221989319255577");
        transaction.setTransactionType("VENDA");
        listTransaction.addResource(transaction);

        Set<ConstraintViolation<ListTransaction>> errors = validateService.validateListTransaction(listTransaction);
        Assertions.assertEquals(1, errors.size());

    }

}
