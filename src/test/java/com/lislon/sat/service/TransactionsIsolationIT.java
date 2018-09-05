package com.lislon.sat.service;

import com.google.testing.threadtester.*;
import com.lislon.sat.model.Account;
import com.lislon.sat.model.TransactionResult;
import com.lislon.sat.model.TransactionStatus;
import org.junit.Assert;
import org.junit.Test;

/**
 * Integration test for transfer funds behaviour in multithread environment.
 * <p>
 * This tests a double spending attack when 2 threads try to sends funds simultaneously.
 * <p>
 * The google weaver will emulate different order of execution inside {@link TransactionsService#transfer} method.
 * <p>
 *     NOTE: For some reason this test wasn't able to catch a deadlock bug
 *     (see TODO in {@link TransactionsService#transfer})
 *
 * {@see http://www.mapdb.org/blog/thread_weaver/}
 */
public class TransactionsIsolationIT {

    private volatile TransactionsService service;
    private volatile Account sender;
    private volatile Account recipient;

    private volatile TransactionResult result1;
    private volatile TransactionResult result2;

    @Test
    public void testPutIfAbsent() {
        // Create an AnnotatedTestRunner that will run the threaded tests defined in
        // this class. These tests are expected to makes calls to NameManager.
        AnnotatedTestRunner runner = new AnnotatedTestRunner();
        runner.runTests(this.getClass(), TransactionsService.class);
    }

    /**
     * Prepare the test.
     */
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
        boolean isFirstTxFailed = result1.getStatus() == TransactionStatus.INSUFFICIENT_FUNDS;
        boolean isSecondTxFailed = result2.getStatus() == TransactionStatus.INSUFFICIENT_FUNDS;
        Assert.assertTrue(isFirstTxFailed || isSecondTxFailed);
    }
}
