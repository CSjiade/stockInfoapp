package com.example.app;
import com.android.volley.Request;

import java.io.IOException;


public class StockModel {

    private String name;
    private String symbol;
    private String description;
    private String price;
    private String logo;
    private String volume;
    private String sales, grossProfit,income,eps,cfo,cfi,cff,ncf,mktcap,fyy;
    private String exchange,listDate,sector;


    public StockModel(String name, String symbol, String sector, String description, String mktcap, String exchange,String listDate) {
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.mktcap = mktcap;
        this.exchange = exchange;
        this.sector = sector;
        this.listDate = listDate;
    }


     public StockModel(String name, String sales,String grossProfit,String income,String  eps,
                       String cfo, String cfi,String cff,String ncf,String fyy) {
        this.name = name;
        this.sales = sales;
        this.grossProfit = grossProfit;
        this.income = income;
        this.eps = eps;
        this.cfo = cfo;
        this.cfi = cfi;
        this.cff = cff;
        this.ncf = ncf;
        this.fyy = fyy;
     }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(String grossProfit) {
        this.grossProfit = grossProfit;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getCfo() {
        return cfo;
    }

    public void setCfo(String cfo) {
        this.cfo = cfo;
    }

    public String getCfi() {
        return cfi;
    }

    public void setCfi(String cfi) {
        this.cfi = cfi;
    }

    public String getCff() {
        return cff;
    }

    public void setCff(String cff) {
        this.cff = cff;
    }

    public String getNcf() {
        return ncf;
    }

    public void setNcf(String ncf) {
        this.ncf = ncf;
    }

    public String getMktcap() {
        return mktcap;
    }

    public void setMktcap(String mktcap) {
        this.mktcap = mktcap;
    }

    public String getFyy() {
        return fyy;
    }

    public void setFyy(String fyy) {
        this.fyy = fyy;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getListDate() {
        return listDate;
    }

    public void setListDate(String listDate) {
        this.listDate = listDate;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
