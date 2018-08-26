package com.test.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.data.db.UserCoinDAO;
import com.test.domain.entity.Coin;

@Entity(tableName = UserCoinDAO.TABLE_NAME)
public class UserCoinResponse implements DataModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private String name;
    private String symbol;
    private double price;
    private double quantity;


    //Constructor for Update coin local db
    public UserCoinResponse(int id, String name, String symbol, double price, double quantity) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
