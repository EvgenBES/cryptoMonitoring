package com.test.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.data.entity.UserCoinResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface UserCoinDAO {
    String TABLE_NAME = "user";

    @Insert
    void insert(UserCoinResponse userCoinResponse); //add coin

    @Update
    void update(UserCoinResponse userCoinResponse);

    @Query("DELETE FROM user WHERE id = :coinId")
    void delete(long coinId);

    @Query("DELETE FROM " + TABLE_NAME)
    void deleteAll();

    @Query("SELECT * FROM " + TABLE_NAME)
    Flowable<List<UserCoinResponse>> getAll();
}
