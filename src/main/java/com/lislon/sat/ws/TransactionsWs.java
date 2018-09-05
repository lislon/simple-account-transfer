package com.lislon.sat.ws;

import com.lislon.sat.error.ResponseFactory;
import com.lislon.sat.model.ApiError;
import com.lislon.sat.model.Transaction;
import com.lislon.sat.model.TransactionDetails;
import com.lislon.sat.service.TransactionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "transactions", description = "Transactions on accounts")
public class TransactionsWs {
    @Inject
    TransactionsService transactionsService;

    @POST
    @Operation(
            summary = "Transfer fund from one account to another",
            tags = {"transactions"},
            description = "Withdraw specified amount of money from first account and deposit it to second account",
            responses = {
                @ApiResponse(
                        responseCode = "200",
                        content = @Content(
                                schema = @Schema(implementation = TransactionDetails.class)),
                        description = "Transaction details with status"
                ),
                @ApiResponse(responseCode = "409", description = "Insufficient funds",
                        content = @Content(
                                schema = @Schema(implementation = ApiError.class))),
                @ApiResponse(responseCode = "404", description = "Sender/Receiver account not exists",
                        content = @Content(
                                schema = @Schema(implementation = ApiError.class))
                ),
            }
    )
    public Response newTransaction(Transaction tx) {
        TransactionDetails details = transactionsService.transfer(tx.getFrom(), tx.getTo(), tx.getAmount());
        return ResponseFactory.getResponseFromTransactionDetails(details);
    }
}
