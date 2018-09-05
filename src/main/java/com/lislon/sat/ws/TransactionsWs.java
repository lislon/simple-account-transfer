package com.lislon.sat.ws;

import com.lislon.sat.model.ApiError;
import com.lislon.sat.model.Transaction;
import com.lislon.sat.model.TransactionResult;
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

import static com.lislon.sat.error.ErrorCodes.*;

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
                                schema = @Schema(implementation = TransactionResult.class)),
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
        TransactionResult result = transactionsService.transfer(tx.getSenderAccountId(), tx.getReceiverAccountId(), tx.getAmount());
        switch (result.getStatus()) {
            case INSUFFICIENT_FUNDS:
                return error(
                        "Client doesn't have enough funds",
                        CODE_ERROR_NOT_ENOUGH_FUNDS,
                        Response.Status.CONFLICT
                );
            case SENDER_ACCOUNT_NOT_EXISTS:
                return error(
                        "Senders account not exists",
                        CODE_SENDER_ACCOUNT_NOT_EXISTS,
                        Response.Status.NOT_FOUND
                );
            case RECEIVER_ACCOUNT_NOT_EXISTS:
                return error(
                        "Receiver account not exists",
                        CODE_RECEIVER_ACCOUNT_NOT_EXISTS,
                        Response.Status.NOT_FOUND
                );
            case SUCCESS:
                return Response.ok().entity(result).build();
            default:
                throw new RuntimeException("Unexpected status " + result.getStatus());
        }
    }

    private static Response error(String userMessage, int errorCode, Response.Status httpCode) {
        return Response
                .status(httpCode)
                .entity(new ApiError(userMessage, errorCode))
                .build();
    }
}
