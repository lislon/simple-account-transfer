package com.lislon.sat.ws;

import com.lislon.sat.error.ErrorCodes;
import com.lislon.sat.model.Account;
import com.lislon.sat.model.ApiError;
import com.lislon.sat.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eclipse.jetty.http.HttpStatus;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by ele on 30.08.2018.
 */
@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "accounts", description = "Adding/Viewing accounts")
public class AccountsWs {
    @Inject
    private AccountsService accountsService;

    @GET
    @Operation(
            summary = "Lists all accounts in system",
            tags = {"accounts"},
            description = "Show all account with their balances"
    )
    public Collection<Account> getAccounts() {
        return accountsService.getAccounts();
    }

    @GET
    @Path("{id}")
    @Operation(
            summary = "Show details for single account by its id",
            tags = {"accounts"},
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = Account.class)),
                            description = "Account details with identifier"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    schema = @Schema(implementation = ApiError.class)),
                            description = "Account cannot be found"
                    )
            }
    )
    public Response get(
            @Parameter(description = "Account identifier", required = true)
            @PathParam("id") Integer id
    ) {
        Account account = accountsService.get(id);
        if (account != null) {
            return Response.ok().entity(account).build();
        } else {
            return Response
                    .status(HttpStatus.NOT_FOUND_404)
                    .entity(new ApiError(
                            String.format("Account with identifier %s not found", id),
                            ErrorCodes.CODE_ACCOUNT_NOT_FOUND))
                    .build();
        }
    }

    @POST
    @Operation(
            summary = "Adds a new account to system",
            tags = {"accounts"},
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            content = @Content(
                                    schema = @Schema(implementation = Account.class)),
                            description = "Account details with assigned identifier"
                    )
            }
    )
    public Response addAccount(
            @Parameter(description = "Details about new account, excluding id field", required = true)
                    Account newAccount
    ) {
        accountsService.add(newAccount);
        return Response.status(HttpStatus.CREATED_201)
                .entity(newAccount)
                .build();
    }
}
