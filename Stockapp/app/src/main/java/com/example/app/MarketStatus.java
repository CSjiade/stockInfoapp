package com.example.app;

public class MarketStatus {

    private String nasdaq,nyse,otc,fx,crypto;

    public MarketStatus(String nasdaq, String nyse, String otc, String fx, String crypto) {
        this.nasdaq = nasdaq;
        this.nyse = nyse;
        this.otc = otc;
        this.fx = fx;
        this.crypto = crypto;
    }

    public String getNasdaq() {
        return nasdaq;
    }

    public void setNasdaq(String nasdaq) {
        this.nasdaq = nasdaq;
    }

    public String getNyse() {
        return nyse;
    }

    public void setNyse(String nyse) {
        this.nyse = nyse;
    }

    public String getOtc() {
        return otc;
    }

    public void setOtc(String otc) {
        this.otc = otc;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getCrypto() {
        return crypto;
    }

    public void setCrypto(String crypto) {
        this.crypto = crypto;
    }
}
