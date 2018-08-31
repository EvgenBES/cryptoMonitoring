package com.test.domain.entity;

public class Coin implements DomainModel{

    private long id;
    private String name;
    private String symbol;
    private long rank;
    private long circulatingSupply;
    private double price;
    private double percentChange24h;
    private long marketCap;
    private double percentChange1h;
    private double volume24h;
    private double percentChange7d;
    private double quantity;
    private boolean motionPrice;
    private double pricePosition;


    //Constructor get all coin local bd
    public Coin(long id, String name, String symbol, double price, double quantity, double pricePosition, boolean motionPrice) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
        this.pricePosition = pricePosition;
        this.motionPrice = motionPrice;
    }


    //Constructor Coins: StartActivity and Left List
    public Coin(long id, String name, String symbol, double price, double quantity) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public Coin(long id, String symbol, double price, double quantity) {
        this.id = id;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    //Constructor Add Notification Coin
    public Coin(long id, String name, String symbol, double pricePosition, boolean motionPrice) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.pricePosition = pricePosition;
        this.motionPrice = motionPrice;
    }

    //Constructor Add Right List
    public Coin(long id, String name, String symbol, double price, long rank) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.rank = rank;
    }

    //Delete
    public Coin(long id) {
        this.id = id;
    }


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

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

    public long getCirculatingSupply() {
        return circulatingSupply;
    }

    public void setCirculatingSupply(long circulatingSupply) {
        this.circulatingSupply = circulatingSupply;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public void setPercentChange24h(double percentChange24h) {
        this.percentChange24h = percentChange24h;
    }

    public long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }

    public double getPercentChange1h() {
        return percentChange1h;
    }

    public void setPercentChange1h(double percentChange1h) {
        this.percentChange1h = percentChange1h;
    }

    public double getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(double volume24h) {
        this.volume24h = volume24h;
    }

    public double getPercentChange7d() {
        return percentChange7d;
    }

    public void setPercentChange7d(double percentChange7d) {
        this.percentChange7d = percentChange7d;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean getMotionPrice() {
        return motionPrice;
    }

    public void setMotionPrice(boolean motionPrice) {
        this.motionPrice = motionPrice;
    }

    public double getPricePosition() {
        return pricePosition;
    }

    public void setPricePosition(double pricePosition) {
        this.pricePosition = pricePosition;
    }
}
