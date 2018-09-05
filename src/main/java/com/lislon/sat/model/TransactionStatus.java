package com.lislon.sat.model;

/**
 * Indicates the status of transfer operation from one account to another.
 */
public enum TransactionStatus {
    /**
     * Transfer completed successfully.
     */
    SUCCESS,

    /**
     * Sender has not enough funds to make a transfer with given amount.
     */
    INSUFFICIENT_FUNDS,

    /**
     * Sender's account id is not exists.
     */
    SENDER_ACCOUNT_NOT_EXISTS,

    /**
     * Invalid amount of transaction (<= 0).
     */
    INVALID_AMOUNT,
    /**
     * Recipient's account id is not exists.
     */
    RECEIVER_ACCOUNT_NOT_EXISTS
}
