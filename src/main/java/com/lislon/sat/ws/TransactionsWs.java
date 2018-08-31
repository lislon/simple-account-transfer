package com.lislon.sat.ws;

import com.lislon.sat.model.Transaction;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transactions")
public class TransactionsWs {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Transaction newTransaction(Transaction tx) {
        tx.setId(1);
        return tx;
    }
}
