package com.lislon.sat.model;

/**
 * Result with detauls of transaction.
 */
public class TransactionDetails {
    public TransactionStatus status;

    public TransactionDetails() {
    }

    public TransactionDetails(TransactionStatus status) {
        this.status = status;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }


    public static TransactionDetails create(TransactionStatus status) {
        return new TransactionDetails(status);
    }
}
