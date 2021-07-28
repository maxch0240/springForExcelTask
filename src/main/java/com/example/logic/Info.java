package com.example.logic;

public class Info {
    private int bAccount;

    private double inActive;
    private double inPassive;

    private double debit;
    private double credit;

    private double outActive;
    private double outPassive;

    private String fileName;

    public Info(int bAccount, double inActive, double inPassive, double debit, double credit, double outActive, double outPassive, String fileName) {
        this.bAccount = bAccount;
        this.inActive = inActive;
        this.inPassive = inPassive;
        this.debit = debit;
        this.credit = credit;
        this.outActive = outActive;
        this.outPassive = outPassive;
        this.fileName = fileName;
    }


    public int getBAccount() {
        return bAccount;
    }

    public void setBAccount(int bAccount) {
        this.bAccount = bAccount;
    }

    public double getInActive() {
        return inActive;
    }

    public void setInActive(double inActive) {
        this.inActive = inActive;
    }

    public double getInPassive() {
        return inPassive;
    }

    public void setInPassive(double inPassive) {
        this.inPassive = inPassive;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getOutActive() {
        return outActive;
    }

    public void setOutActive(double outActive) {
        this.outActive = outActive;
    }

    public double getOutPassive() {
        return outPassive;
    }

    public void setOutPassive(double outPassive) {
        this.outPassive = outPassive;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
