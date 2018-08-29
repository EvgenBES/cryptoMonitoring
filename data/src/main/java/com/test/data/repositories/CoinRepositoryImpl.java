package com.test.data.repositories;

import android.annotation.SuppressLint;
import android.util.Log;

import com.test.data.R;
import com.test.data.db.CoinDataBase;
import com.test.data.entity.CoinResponces;
import com.test.data.entity.NotifCoinResponse;
import com.test.data.entity.UserCoinResponse;
import com.test.data.net.RestService;
import com.test.domain.entity.Coin;
import com.test.domain.entity.Error;
import com.test.domain.entity.ErrorType;
import com.test.domain.entity.Search;
import com.test.domain.repositories.CoinRepository;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class CoinRepositoryImpl implements CoinRepository {

    private RestService restService;
    private CoinDataBase coinDataBase;
    private static long lastCoinUpdateTime = 0;
    private final long TIMER_UPDATE_COIN = 300000;

    @Inject
    public CoinRepositoryImpl(RestService restService, CoinDataBase coinDataBase) {
        this.restService = restService;
        this.coinDataBase = coinDataBase;
    }

//    @Override
//    public Observable<List<Coin>> getAll() {
//        return restService
//                .getAllCoin()
//                .map(new Function<List<CoinResponces>, List<Coin>>() {
//                    @Override
//                    public List<Coin> apply(List<CoinResponces> testItems) throws Exception {
//                        Log.e("AAQQ", "apply: 2");
//                        coinDataBase.getCoinDAO().deleteAll();
//
//                        final List<Coin> list = new ArrayList<>();
//                        for (CoinResponces coin : testItems) {
//                            list.add(new Coin(
//                                    coin.getName(),
//                                    coin.getSymbol(),
//                                    coin.getPrice()));
//
//                            coinDataBase.getCoinDAO().insert(coin);
//                            Log.e("AAQQ", "apply: 4");
//
//                        }
//                        return list;
//                    }
//                });
//    }


    @Override
    public Flowable<List<Coin>> getAll() {
        return coinDataBase
                .getCoinDAO()
                .getAll()
                .flatMap(new Function<List<CoinResponces>, Publisher<List<CoinResponces>>>() {
                    @Override
                    public Publisher<List<CoinResponces>> apply(List<CoinResponces> coinResponces) throws Exception {
                        if (coinResponces.isEmpty() ||
                                (lastCoinUpdateTime + TIMER_UPDATE_COIN) < System.currentTimeMillis()) {

                            return restService
                                    .getAllCoin()
                                    .doOnNext(new Consumer<List<CoinResponces>>() {
                                        @Override
                                        public void accept(List<CoinResponces> coinResponces) throws Exception {
                                            lastCoinUpdateTime = System.currentTimeMillis();
                                            coinDataBase.getCoinDAO().deleteAll();

                                            for (CoinResponces coinResponce : coinResponces) {
                                                coinDataBase.getCoinDAO().insert(coinResponce);
                                            }
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, Publisher<List<CoinResponces>>>() {
                                        @Override
                                        public Publisher<List<CoinResponces>> apply(Throwable throwable) throws Exception {
                                            if (throwable instanceof Error) {
                                                Error error = (Error) throwable;
                                                Log.e("AAQQ", "apply: ТУТ 1" );
                                                if (error.getErrorType() == ErrorType.INTERNET_IS_NOT_AVAILABLE
                                                        && !coinResponces.isEmpty()) {
                                                    return Flowable.just(coinResponces);

                                                }
                                            }
                                            return Flowable.just(coinResponces);
//                                            return Flowable.error(throwable);
                                        }
                                    });
                        } else {
                            return Flowable.just(coinResponces);
                        }
                    }
                })
                .map(new Function<List<CoinResponces>, List<Coin>>() {
                    @Override
                    public List<Coin> apply(List<CoinResponces> coinResponces) throws Exception {
                        final List<Coin> list = new ArrayList<>();
                        for (CoinResponces coin : coinResponces) {
                            list.add(new Coin(
                                    coin.getName(),
                                    coin.getSymbol(),
                                    coin.getPrice()));

                        }
                        return list;
                    }
                });
    }


    @Override
    public Flowable<List<Coin>> updateLocalBd() {
        return coinDataBase
                .getCoinDAO()
                .getAll()
                .map(new Function<List<CoinResponces>, List<Coin>>() {
                    @Override
                    public List<Coin> apply(List<CoinResponces> coinResponces) throws Exception {
                        final List<Coin> list = new ArrayList<>();
                        for (CoinResponces coin : coinResponces) {
                            list.add(new Coin(
                                    coin.getName(),
                                    coin.getSymbol(),
                                    coin.getPrice()));
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
                0,
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
