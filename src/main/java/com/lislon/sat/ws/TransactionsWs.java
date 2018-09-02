package com.lislon.sat.ws;

import com.lislon.sat.model.Transaction;
import com.lislon.sat.model.TransferResult;
import com.lislon.sat.service.TransactionsService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transactions")
public class TransactionsWs {
    @Inject
    TransactionsService transactionsService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Transaction newTransaction(Transaction tx) {
        TransferResult transfer = transactionsService.transfer(tx.getFrom(), tx.getTo(), tx.getAmount());
        tx.setId(1);
        return tx;
    }
}
