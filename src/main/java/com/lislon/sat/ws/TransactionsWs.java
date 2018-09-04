package com.lislon.sat.ws;

import com.lislon.sat.model.Transaction;
import com.lislon.sat.model.TransferResult;
import com.lislon.sat.service.TransactionsService;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionsWs {
    @Inject
    TransactionsService transactionsService;

    @POST
    @Operation(
            summary = "Transfer fund from one account to another",
            tags = {"transactions"},
            description = "Withdraw specified amount of money from first account and deposit it to second account"
    )
    public Transaction newTransaction(Transaction tx) {
        TransferResult transfer = transactionsService.transfer(tx.getFrom(), tx.getTo(), tx.getAmount());
        tx.setId(1);
        return tx;
    }
}
