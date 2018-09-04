package com.lislon.sat.mockdata;

import com.lislon.sat.model.Account;

import java.util.Arrays;
import java.util.List;

/**
 * Storage for pre-configured accounts.
 */
public class PredefinedAccounts {

    public static List<Account> getPredefinedAccounts() {
        return Arrays.asList(
                new Account(1, 100),
                new Account(2, 200)
        );
    }
}
