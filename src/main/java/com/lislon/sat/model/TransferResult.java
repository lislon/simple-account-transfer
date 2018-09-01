package com.lislon.sat.model;

/**
 * Indicates the result of transfer operation from one account to another.
 */
public enum TransferResult {
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
     * Recipient's account id is not exists.
     */
    RECEIVER_ACCOUNT_NOT_EXISTS
}
