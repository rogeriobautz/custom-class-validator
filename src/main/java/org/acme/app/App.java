package org.acme.app;

import org.acme.model.ListTransaction;
import org.acme.model.Transaction;
import org.acme.response.ResponseListTransaction;
import org.acme.response.ResponseTransaction;
import org.acme.service.ValidateService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

//import org.slf4j.Logger;

public class App {
    @Inject
    ValidateService validateService;

    @GET
    @Path("/transaction")
    public ResponseTransaction validateOneTransaction(Transaction transaction){
        return validateService.validateTransaction(transaction);

    }

    @GET
    @Path("/list-transaction")
    public ResponseListTransaction validateListTransaction(ListTransaction listTransaction){
        return validateService.validateListAndEveryTransaction(listTransaction);
    }
}


 
   

