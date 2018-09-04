package com.test.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.data.entity.CoinResponces;
import com.test.data.entity.CoinResponse;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CoinDAO {
    String TABLE_NAME = "coins";

    @Insert
    void insert(CoinResponces coinResponces); //add coin

    @Update
    void update(CoinResponces coinResponces);

    @Delete
    void delete(CoinResponces coinResponces);

    @Query("SELECT * FROM " + TABLE_NAME + " WHERE name LIKE :search")
    Flowable<List<CoinResponces>> searchName(String search);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<CoinResponces>> getAll();
}
