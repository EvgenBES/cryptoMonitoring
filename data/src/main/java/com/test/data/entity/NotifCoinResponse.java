package com.test.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.data.db.NotifCoinDAO;

@Entity(tableName = NotifCoinDAO.TABLE_NAME)
public class NotifCoinResponse implements DataModel {


    @PrimaryKey(autoGenerate = true)
    private int idCoin;

    private String name;
    private String symbol;
    private double pricePosition;
    private boolean motionPrice;


    public NotifCoinResponse(int idCoin, String name, String symbol, double pricePosition, boolean motionPrice) {
        this.idCoin = idCoin;
        this.name = name;
        this.symbol = symbol;
        this.pricePosition = pricePosition;
        this.motionPrice = motionPrice;
    }

    public int getIdCoin() {
        return idCoin;
    }

    public void setIdCoin(int idCoin) {
        this.idCoin = idCoin;
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

    public double getPricePosition() {
        return pricePosition;
    }

    public void setPricePosition(double pricePosition) {
        this.pricePosition = pricePosition;
    }

    public boolean getMotionPrice() {
        return motionPrice;
    }

    public void setMotionPrice(boolean motionPrice) {
        this.motionPrice = motionPrice;
    }
}
