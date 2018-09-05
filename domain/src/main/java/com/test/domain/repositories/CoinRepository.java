package com.test.domain.repositories;

import com.test.domain.entity.Coin;
import com.test.domain.entity.Search;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface CoinRepository {

    //GET Internet
    Flowable<List<Coin>> getAll();


    //GET Local BD UserCoin
    Flowable<List<Coin>> getBdCoin();

    void addUserCoin(Coin coin);

    void update(Coin coin);

    void deleteUserCoin(long coinId);


    //GET Local BD NotifUser
    Flowable<List<Coin>> getAllNotif();

    Flowable<List<Coin>> search(String coinName);

    void addNotif(Coin coin);

    void editNotif(int idNotif, double price, boolean motionPrice);

    void deleteNotif(long coinId);
}
