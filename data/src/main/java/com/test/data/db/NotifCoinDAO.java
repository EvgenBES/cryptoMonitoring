package com.test.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.data.entity.NotifCoinResponse;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface NotifCoinDAO {
    String TABLE_NAME = "notif";

    @Insert
    void insert(NotifCoinResponse notifCoinResponse); //add coin

    @Query("UPDATE " + TABLE_NAME + " SET pricePosition = :price, motionPrice = :motionPrice WHERE idNotif = :idNotif")
    void editNitif(int idNotif, double price, boolean motionPrice);

    @Query("DELETE FROM " + TABLE_NAME + " WHERE idNotif = :coinId")
    void delete(long coinId);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<NotifCoinResponse>> getAllNotif();
}
