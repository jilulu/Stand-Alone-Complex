package model;

import static api.purchaserecord.DatabaseHelper.Table.DEFINITION_PAYMENT_RECORD;
import static api.purchaserecord.DatabaseHelper.Table.DEFINITION_PURCHASE_STATUS;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLPurchaseRecordImpl implements IPurchaseRecord {
    private int id, bookId, userId, quantity, purchaseStatus, paymentMethod;

    public SQLPurchaseRecordImpl(int id, int bookId, int quantity, int purchaseStatus, int paymentMethod, int userId) {
        if (quantity <= 0 || purchaseStatus < 0 || purchaseStatus >= DEFINITION_PURCHASE_STATUS.length ||
                paymentMethod < 0 || paymentMethod >= DEFINITION_PAYMENT_RECORD.length) {
            throw new IllegalArgumentException("Illegal arguments supplied. ");
        }
        this.id = id;
        this.bookId = bookId;
        this.quantity = quantity;
        this.purchaseStatus = purchaseStatus;
        this.paymentMethod = paymentMethod;
        this.userId = userId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getBookId() {
        return bookId;
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public int getPurchaseStatus() {
        return purchaseStatus;
    }

    @Override
    public int getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String getPurchaseStatusString() {
        if (purchaseStatus >= 0 && purchaseStatus < DEFINITION_PURCHASE_STATUS.length) {
            return DEFINITION_PURCHASE_STATUS[purchaseStatus];
        } else {
            return null;
        }
    }

    @Override
    public String getPaymentMethodString() {
        if (paymentMethod >= 0 && paymentMethod < DEFINITION_PAYMENT_RECORD.length) {
            return DEFINITION_PAYMENT_RECORD[paymentMethod];
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SQLPurchaseRecordImpl)) {
            return false;
        }
        SQLPurchaseRecordImpl sqlPurchaseRecord = (SQLPurchaseRecordImpl) obj;
        return (id == sqlPurchaseRecord.id) &&
                (bookId == sqlPurchaseRecord.bookId) &&
                (userId == sqlPurchaseRecord.userId) &&
                (quantity == sqlPurchaseRecord.quantity) &&
                (purchaseStatus == sqlPurchaseRecord.purchaseStatus) &&
                (paymentMethod == sqlPurchaseRecord.paymentMethod);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + id;
        result = result * 31 + bookId;
        result = result * 31 + userId;
        result = result * 31 + quantity;
        result = result * 31 + purchaseStatus;
        result = result * 31 + paymentMethod;
        return result;
    }
}
