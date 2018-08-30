package com.test.domain.Model;



public class Currency{
    public float price;
    public double volume_24h;
    public float percent_change_24h;
    public float percent_change_1h;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public double getVolume_24h() {
        return volume_24h;
    }

    public void setVolume_24h(double volume_24h) {
        this.volume_24h = volume_24h;
    }

    public float getPercent_change_24h() {
        return percent_change_24h;
    }

    public void setPercent_change_24h(float percent_change_24h) {
        this.percent_change_24h = percent_change_24h;
    }

    public float getPercent_change_1h() {
        return percent_change_1h;
    }
}
