package com.lislon.sat.ws;

import com.lislon.sat.model.Account;
import com.lislon.sat.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by ele on 30.08.2018.
 */
@Path("/accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
            tags = {"accounts"}
    )
    public Account get(@PathParam("id") Integer id) {
        return accountsService.get(id);
    }

    @POST
    @Operation(
            summary = "Adds a new account to system",
            tags = {"accounts"}
    )
    public Account addAccount(Account newAccount) {
        accountsService.add(newAccount);
        return newAccount;
    }
}
