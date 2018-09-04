package com.lislon.sat.service;

import com.lislon.sat.model.Account;
import org.jvnet.hk2.annotations.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory storage of accounts.
 *
 * This class is thread-safe.
 */
@Service
public class AccountsService {

    private ConcurrentHashMap<Integer, Account> accounts = new ConcurrentHashMap<>();

    public AccountsService() {
    }

    public void addMany(List<Account> accounts) {
        accounts.forEach(this::add);
    }

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(accounts.values());
    }

    /**
     * Adds account into collection and assign new id to it.
     *
     * @param account Account to be added.
     */
    public synchronized void add(Account account) {
        account.setId(accounts.size() + 1);
        accounts.put(account.getId(), account);
    }

    public Account get(int accountId) {
        return accounts.get(accountId);
    }
}
