package com.test.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.data.entity.CoinResponse;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CoinDAO {
    String TABLE_NAME = "coins";

    @Insert
    void insert(CoinResponse coinResponse); //add coin

    @Update
    void update(CoinResponse coinResponse);

    @Delete
    void delete(CoinResponse coinResponse);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<CoinResponse>> getAll();
}
