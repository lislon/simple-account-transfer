package com.lislon.sat.service;

import com.google.testing.threadtester.*;
import com.lislon.sat.model.Account;
import com.lislon.sat.model.TransferResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TransactionsIsolationIT {

    private volatile TransactionsService service;
    private volatile Account sender;
    private volatile Account recipient;

    private volatile TransferResult result1;
    private volatile TransferResult result2;

    @Test
    public void testPutIfAbsent() {
        // Create an AnnotatedTestRunner that will run the threaded tests defined in
        // this class. These tests are expected to makes calls to NameManager.
        AnnotatedTestRunner runner = new AnnotatedTestRunner();
        runner.runTests(this.getClass(), TransactionsService.class);
    }

    @ThreadedBefore
    public void before() {
        AccountsService accountsService = new AccountsService();
        service = new TransactionsService(accountsService);

        sender = Account.createWithBalance(50);
        recipient = Account.createWithBalance(0);
        accountsService.add(sender);
        accountsService.add(recipient);
    }

    @ThreadedMain
    public void mainThread() {
        result1 = service.transfer(sender.getId(), recipient.getId(), 50);
    }

    @ThreadedSecondary
    public void secondThread() {
        result2 = service.transfer(sender.getId(), recipient.getId(), 50);
    }

    @ThreadedAfter
    public void after() {
        Assert.assertTrue(result1 == TransferResult.INSUFFICIENT_FUNDS || result2 == TransferResult.INSUFFICIENT_FUNDS);
    }
}
