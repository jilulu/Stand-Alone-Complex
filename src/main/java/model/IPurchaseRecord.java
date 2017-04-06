package model;

/**
 * Created by jamesji on 05/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public interface IPurchaseRecord {
    int getId();

    int getBookId();

    int getUserId();

    int getQuantity();

    int getPurchaseStatus();

    int getPaymentMethod();

    double getPrice();

    String getPurchaseStatusString();

    String getPaymentMethodString();
}
