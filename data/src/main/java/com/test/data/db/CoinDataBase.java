package com.test.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.test.data.entity.CoinResponces;
import com.test.data.entity.CoinResponse;
import com.test.data.entity.NotifCoinResponse;
import com.test.data.entity.UserCoinResponse;

@Database(entities = {
        CoinResponces.class,
        UserCoinResponse.class,
        NotifCoinResponse.class}, version = 1)
public abstract class CoinDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "coin.db";


    public static CoinDataBase getInstance(Context context) {
        CoinDataBase database = Room.databaseBuilder(context, CoinDataBase.class, DATABASE_NAME)
                //fallbackToDestructiveMigration - говорит о том что бы удалить старую базу, и обновить новую если пришли данные из инета
                .fallbackToDestructiveMigration()
                .build();

        return database;
    }

    public abstract CoinDAO getCoinDAO();
    public abstract UserCoinDAO getUserDAO();
    public abstract NotifCoinDAO getNotifDAO();
}
