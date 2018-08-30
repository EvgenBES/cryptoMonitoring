
package com.test.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quotes {

    @SerializedName("USD")
    @Expose
    private USD uSD;
    @SerializedName(value = "base",alternate = {"AUD", "BRL", "CAD", "CHF", "CLP", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PKR", "PLN", "RUB", "SEK", "SGD", "THB", "TRY", "TWD", "ZAR"})
    @Expose
    private BASE base;

    public USD getUSD() {
        return uSD;
    }

    public void setUSD(USD uSD) {
        this.uSD = uSD;
    }

    public BASE getBASE() {
        return base;
    }

    public void setEUR(BASE base) {
        this.base = base;
    }

}
