package com.test.data.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.test.data.db.NotifCoinDAO;

@Entity(tableName = NotifCoinDAO.TABLE_NAME)
public class NotifCoinResponse implements DataModel {


    @PrimaryKey(autoGenerate = true)
    private int idNotif;


    private long id;
    private String name;
    private double pricePosition;
    private boolean motionPrice;


    public NotifCoinResponse(int idNotif, long id, String name, double pricePosition, boolean motionPrice) {
        this.idNotif = idNotif;
        this.id = id;
        this.name = name;
        this.pricePosition = pricePosition;
        this.motionPrice = motionPrice;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdNotif() {
        return idNotif;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
