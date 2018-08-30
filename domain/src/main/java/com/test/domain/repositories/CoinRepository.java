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
    Single<List<Coin>> getAll();




    //GET Local BD UserCoin
    Flowable<List<Coin>> getBdCoin();

    void addUserCoin(Coin coin);

    void update(Coin coin);

    void deleteUserCoin(long coinId);



    //GET Local BD NotifUser
    Flowable<List<Coin>> getAllNotif();

    void addNotif(Coin coin);

    void deleteNotif(long coinId);









//    Observable<Coin> getOneUser(String id);

    Completable delete(String id);

    Observable<List<Coin>> search(Search search);
}
