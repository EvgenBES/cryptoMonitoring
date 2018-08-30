package com.test.data.repositories;

import com.test.data.db.CoinDataBase;
import com.test.data.entity.NotifCoinResponse;
import com.test.data.entity.UserCoinResponse;
import com.test.data.model.Currency;
import com.test.data.net.RestService;
import com.test.domain.entity.Coin;
import com.test.domain.entity.Search;
import com.test.domain.repositories.CoinRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CoinRepositoryImpl implements CoinRepository {

    private RestService restService;
    private CoinDataBase coinDataBase;
    private long lastUserUpdateTime = 0;

    @Inject
    public CoinRepositoryImpl(RestService restService, CoinDataBase coinDataBase) {
        this.restService = restService;
        this.coinDataBase = coinDataBase;
    }

    @Override
    public Single<List<Coin>> getAll() {
        return restService
                .getAllCoin()
                .map(new Function<Currency, List<Coin>>() {

                    @Override
                    public List<Coin> apply(Currency currency) throws Exception {
                        List<Coin> list = new ArrayList<>();

                        for (int i = 0; i < currency.getData().size(); i++) {
                            list.add(new Coin(
                                    currency.getData().get(i).getId(),
                                    currency.getData().get(i).getName(),
                                    currency.getData().get(i).getSymbol(),
                                    currency.getData().get(i).getQuotes().getUSD().getPrice(),
                                    0
                            ));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Flowable<List<Coin>> getBdCoin() {
        return coinDataBase
                .getUserDAO()
                .getAll()
                .map(new Function<List<UserCoinResponse>, List<Coin>>() {
                    @Override
                    public List<Coin> apply(List<UserCoinResponse> userCoinResponses) throws Exception {
                        final List<Coin> list = new ArrayList<>();
                        for (UserCoinResponse coin : userCoinResponses) {
                            list.add(new Coin(
                                    coin.getId(),
                                    coin.getName(),
                                    coin.getSymbol(),
                                    coin.getPrice(),
                                    coin.getQuantity()));
                        }
                        return list;
                    }
                });
    }

    @Override
    public void addUserCoin(Coin coin) {
        UserCoinResponse userCoin = new UserCoinResponse(
                coin.getId(),
                coin.getName(),
                coin.getSymbol(),
                coin.getPrice(),
                coin.getQuantity()
        );

        Observable.just(coinDataBase)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CoinDataBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CoinDataBase coinDataBase) {
                        coinDataBase.getUserDAO().delete(userCoin.getId());
                        coinDataBase.getUserDAO().insert(userCoin);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public Flowable<List<Coin>> getAllNotif() {
        return coinDataBase
                .getNotifDAO()
                .getAllNotif()
                .map(new Function<List<NotifCoinResponse>, List<Coin>>() {
                    @Override
                    public List<Coin> apply(List<NotifCoinResponse> notifCoinResponses) throws Exception {
                        final List<Coin> list = new ArrayList<>();
                        for (NotifCoinResponse coin : notifCoinResponses) {
                            list.add(new Coin(
                                    coin.getIdCoin(),
                                    coin.getName(),
                                    coin.getSymbol(),
                                    coin.getPricePosition(),
                                    coin.getMotionPrice()
                            ));
                        }
                        return list;
                    }
                });
    }

    @Override
    public void update(Coin coin) {

        UserCoinResponse editCoin = new UserCoinResponse(
                (int) coin.getId(),
                coin.getName(),
                coin.getSymbol(),
                coin.getPrice(),
                coin.getQuantity()
        );

        Observable.just(coinDataBase)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CoinDataBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CoinDataBase coinDataBase) {
                        coinDataBase.getUserDAO().update(editCoin);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addNotif(Coin coin) {
        NotifCoinResponse addNotifCoin = new NotifCoinResponse(
                (int) coin.getId(),
                coin.getName(),
                coin.getSymbol(),
                coin.getPricePosition(),
                coin.getMotionPrice()
        );

        Observable.just(coinDataBase)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CoinDataBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CoinDataBase coinDataBase) {
                        coinDataBase.getNotifDAO().insert(addNotifCoin);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


//    @Override
//    public Observable<Coin> getOneUser(String id) {
//        return restService
//                .getCoin(id)
//                .map(new Function<List<UserCoinResponse>, Coin>() {
//                    @Override
//                    public Coin apply(List<UserCoinResponse> userCoinResponses) throws Exception {
//                        for (UserCoinResponse userCoinRespons : userCoinResponses) {
//                            coinDataBase.getUserDAO().insert(userCoinRespons);
//                        }
//                        return null;
//                    }
//                });
//    }

    @Override
    public Completable delete(String id) {
        return null;
    }

    @Override
    public void deleteNotif(long coinId) {
        Observable.just(coinDataBase)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CoinDataBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CoinDataBase coinDataBase) {
                        coinDataBase.getNotifDAO().delete(coinId);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void deleteUserCoin(long coinId) {
        Observable.just(coinDataBase)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CoinDataBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CoinDataBase coinDataBase) {
                        coinDataBase.getUserDAO().delete(coinId);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @Override
    public Observable<List<Coin>> search(Search search) {
        return null;
    }


    private String getSortValueByBoolen(Boolean isSortAcs) {
        if (isSortAcs) {
            return "acs";
        } else {
            return "desc";
        }
    }
}
