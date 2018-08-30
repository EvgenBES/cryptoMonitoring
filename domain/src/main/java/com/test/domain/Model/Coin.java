package com.test.domain.Model;

public class Coin {

    int id;
    String name;
    int rank;
    Quotes quotes;
    long last_updated;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public Quotes getQuotes() {
        return quotes;
    }

    public long getLast_updated() {
        return last_updated;
    }
}

