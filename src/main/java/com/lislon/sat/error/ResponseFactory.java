package com.lislon.sat.error;

import com.lislon.sat.model.Account;
import com.lislon.sat.model.ApiError;
import com.lislon.sat.model.TransactionDetails;

import javax.ws.rs.core.Response;

import static com.lislon.sat.error.ErrorCodes.CODE_ERROR_NOT_ENOUGH_FUNDS;
import static com.lislon.sat.error.ErrorCodes.CODE_RECEIVER_ACCOUNT_NOT_EXISTS;
import static com.lislon.sat.error.ErrorCodes.CODE_SENDER_ACCOUNT_NOT_EXISTS;

public class ResponseFactory {

    public static Response getResponseFromTransactionDetails(TransactionDetails details) {
        switch (details.getStatus()) {
            case INSUFFICIENT_FUNDS:
                return error(
                        "Client doesn't have enough funds to made transfer",
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
                return ok(details);
            default:
                throw new RuntimeException("Unexpected status " + details.getStatus());
        }
    }

    private static Response ok(TransactionDetails details) {
        return Response
                .status(Response.Status.OK)
                .entity(details).build();
    }

    private static Response error(String userMessage, int errorCode, Response.Status httpCode) {
        return Response
                .status(httpCode)
                .entity(new ApiError(userMessage, errorCode))
                .build();
    }
}
