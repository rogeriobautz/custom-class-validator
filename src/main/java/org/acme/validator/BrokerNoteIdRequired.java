package org.acme.validator;

import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = BrokerNoteIdRequiredValidator.class)
@Documented
public @interface BrokerNoteIdRequired {

    String message() default "{Broker Note is mandatory in transaction types {transactionType.toString()}}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] transactionType();
}