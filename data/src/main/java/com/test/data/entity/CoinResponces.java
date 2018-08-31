package com.test.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.test.data.db.CoinDAO;

@Entity(tableName = CoinDAO.TABLE_NAME)
public class CoinResponces {


    @PrimaryKey
    private long id;

    private String name;
    private String symbol;
    private int rank;
    @SerializedName("price_usd")
    private double price;
    @SerializedName("market_cap_usd")
    private long marketCapUsd;
    @SerializedName("available_supply")
    private long availableSupply;
    @SerializedName("total_supply")
    private long totalSupply;
    @SerializedName("percent_change_1h")
    private double percentChange1h;
    @SerializedName("percent_change_24h")
    private double percentChange24h;
    @SerializedName("percent_change_7d")
    private double percentChange7d;
    @SerializedName("last_updated")
    private long lastUpdated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getMarketCapUsd() {
        return marketCapUsd;
    }

    public void setMarketCapUsd(long marketCapUsd) {
        this.marketCapUsd = marketCapUsd;
    }

    public long getAvailableSupply() {
        return availableSupply;
    }

    public void setAvailableSupply(long availableSupply) {
        this.availableSupply = availableSupply;
    }

    public long getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(long totalSupply) {
        this.totalSupply = totalSupply;
    }

    public double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
