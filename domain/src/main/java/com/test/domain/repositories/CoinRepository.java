package com.test.domain.repositories;

import com.test.domain.entity.Coin;
import com.test.domain.entity.Search;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

public interface CoinRepository {

    Observable<List<Coin>> getAll();

    Flowable<List<Coin>> getBdCoin();

    Flowable<List<Coin>> getAllNotif();

    void update(Coin coin);

    void addNotif(Coin coin);

    Observable<Coin> getOneUser(String id);

    Completable delete(String id);

    void deleteNotif(long coinId);

    void deleteUserCoin(long coinId);

    Observable<List<Coin>> search(Search search);
}
