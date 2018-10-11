package de.netalic.myapplication.data.model;

import java.io.Serializable;

public class Wallet implements Serializable {

    private String currencyCode;
    private int id;
    private double spendableBalance;
    private String modifiedAt;
    private String name;
    private String createdAt;
    private int ownerId;
    private double balance;
    private String address;
    private String currencySymbol;
}
