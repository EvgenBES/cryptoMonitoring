package com.test.domain.entity;

public class ItemTopStart {
    String symbol;
    Double sumPrice;

    public ItemTopStart(String symbol, Double sumPrice) {
        this.symbol = symbol;
        this.sumPrice = sumPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(Double sumPrice) {
        this.sumPrice = sumPrice;
    }
}
