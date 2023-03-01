package com.example.pigeonbreedermanagementapplication.Transaction;

public class TransactionGetSet {

    private int transaction_id;
    private String transaction_type;
    private String transaction_date;
    private String transaction_partner;
    private int transaction_amount;
    private String transaction_details;
    private int profile_id;


    public TransactionGetSet(int transaction_id, String transaction_type, String transaction_date, String transaction_partner, int transaction_amount, String transaction_details, int profile_id) {
        this.transaction_id = transaction_id;
        this.transaction_type = transaction_type;
        this.transaction_date = transaction_date;
        this.transaction_partner = transaction_partner;
        this.transaction_amount = transaction_amount;
        this.transaction_details = transaction_details;
        this.profile_id = profile_id;
    }

    @Override
    public String toString() {
        return "TransactionGetSet{" +
                "transaction_id=" + transaction_id +
                ", transaction_type='" + transaction_type + '\'' +
                ", transaction_date='" + transaction_date + '\'' +
                ", transaction_partner='" + transaction_partner + '\'' +
                ", transaction_amount=" + transaction_amount +
                ", transaction_details='" + transaction_details + '\'' +
                ", profile_id=" + profile_id +
                '}';
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_partner() {
        return transaction_partner;
    }

    public void setTransaction_partner(String transaction_partner) {
        this.transaction_partner = transaction_partner;
    }

    public int getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(int transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTransaction_details() {
        return transaction_details;
    }

    public void setTransaction_details(String transaction_details) {
        this.transaction_details = transaction_details;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }
}
