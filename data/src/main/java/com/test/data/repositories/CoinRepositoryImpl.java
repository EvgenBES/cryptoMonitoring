package com.test.data.repositories;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.test.data.db.CoinDataBase;
import com.test.data.entity.CoinResponces;
import com.test.data.entity.NotifCoinResponse;
import com.test.data.entity.UserCoinResponse;
import com.test.data.model.Currency;
import com.test.data.net.RestService;
import com.test.domain.entity.Coin;
import com.test.domain.entity.Error;
import com.test.domain.entity.ErrorType;
import com.test.domain.entity.Search;
import com.test.domain.repositories.CoinRepository;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
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


    @Override
    public Flowable<List<Coin>> getAll() {
        return coinDataBase
                .getCoinDAO()
                .getAll()
                .flatMap(new Function<List<CoinResponces>, Publisher<List<CoinResponces>>>() {
                    @Override
                    public Publisher<List<CoinResponces>> apply(List<CoinResponces> coinResponces) throws Exception {
                        Currency currency = new Currency();

                        //Проверяем пусто ли в локальной бд? Или же если время обновление бд привысило 5 минут
                        //Если привысило или пусто, тогда грузим их с API
                        if (coinResponces.isEmpty() ||
                                (lastCoinUpdateTime + TIMER_UPDATE_COIN) < System.currentTimeMillis()) {
                            return restService
                                    .getAllCoin()
                                    .doOnNext(new Consumer<Currency>() {
                                        @Override
                                        public void accept(Currency currency) throws Exception {
                                            lastCoinUpdateTime = System.currentTimeMillis();
                                            coinDataBase.getCoinDAO().deleteAll();
                                            CoinResponces coindRespons = new CoinResponces();

                                            for (int i = 0; i < currency.getData().size(); i++) {

                                                coindRespons.setId(currency.getData().get(i).getId());
                                                coindRespons.setName(currency.getData().get(i).getName());
                                                coindRespons.setSymbol(currency.getData().get(i).getSymbol());
                                                coindRespons.setPrice(currency.getData().get(i).getQuotes().getUSD().getPrice());
                                                coindRespons.setRank(currency.getData().get(i).getRank());

                                                coinDataBase.getCoinDAO().insert(coindRespons);
                                            }
                                        }
                                    })
                                    .onErrorResumeNext(new Function<Throwable, Publisher<Currency>>() {
                                        @Override
                                        public Publisher<Currency> apply(Throwable throwable) throws Exception {
                                            Log.e("AAQQ", "apply: Error позиция 1" + throwable.getMessage().toString());
                                            if (throwable instanceof Error) {
                                                Error error = (Error) throwable;
                                                if (error.getErrorType() == ErrorType.INTERNET_IS_NOT_AVAILABLE
                                                        && !coinResponces.isEmpty()) {
                                                    Log.e("AAQQ", "apply: Error позиция 2");
                                                    return Flowable.just(currency);
                                                }
                                            }
                                            Log.e("AAQQ", "apply: Error позиция 3");
                                            return Flowable.just(currency);
                                        }
                                    })
                                    .flatMap(new Function<Currency, Publisher<List<CoinResponces>>>() {
                                        @Override
                                        public Publisher<List<CoinResponces>> apply(Currency currency) throws Exception {
                                            return Flowable.just(coinResponces);
                                        }
                                    });

                        } else {
                            return Flowable.just(coinResponces);
                        }
                    }
                })
                .map(new Function<List<CoinResponces>, List<Coin>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public List<Coin> apply(List<CoinResponces> coinResponces) throws Exception {
                        final List<Coin> list = new ArrayList<>();
                        for (CoinResponces coin : coinResponces) {
                            list.add(new Coin(
                                    coin.getId(),
                                    coin.getName(),
                                    coin.getSymbol(),
                                    coin.getPrice(),
                                    coin.getRank()));
                        }

                        list.sort(new Comparator<Coin>() {
                            @Override
                            public int compare(Coin coin, Coin coin2) {
                                return Long.compare(coin.getRank(), coin2.getRank());
                            }
                        });

                        return list;
                    }
                });
    }

    @Override
    public Flowable<List<Coin>> getListCoinDao() {
        return coinDataBase
                .getCoinDAO()
                .getAll()
                .map(new Function<List<CoinResponces>, List<Coin>>() {
                    @Override
                    public List<Coin> apply(List<CoinResponces> coinResponces) throws Exception {
                        final List<Coin> list = new ArrayList<>();
                        for (CoinResponces coin : coinResponces) {
                            list.add(new Coin(
                                    coin.getId(),
                                    coin.getName(),
                                    coin.getSymbol(),
                                    coin.getPrice(),
                                    coin.getRank()));
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
                    @RequiresApi(api = Build.VERSION_CODES.N)
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

                        list.sort(new Comparator<Coin>() {
                            @Override
                            public int compare(Coin coin, Coin coin2) {
                                return Double.compare(coin.getPrice() * coin.getQuantity(), coin2.getPrice() * coin2.getQuantity());
                            }
                        });

                        Collections.reverse(list);

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
                                    coin.getIdNotif(),
                                    coin.getId(),
                                    coin.getName(),
                                    coin.getPricePosition(),
                                    coin.getMotionPrice()
                            ));
                        }
                        return list;
                    }
                });
    }

    @Override
    public Flowable<List<Coin>> search(String coinName) {
        return coinDataBase
                .getCoinDAO()
                .searchName(coinName)
                .map(new Function<List<CoinResponces>, List<Coin>>() {
                    @Override
                    public List<Coin> apply(List<CoinResponces> coinResponces) throws Exception {
                        final List<Coin> list = new ArrayList<>();

                        for (CoinResponces coin : coinResponces) {
                            list.add(new Coin(
                                    coin.getId(),
                                    coin.getName(),
                                    coin.getPrice(),
                                    0
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
                0,
                coin.getId(),
                coin.getName(),
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

    @Override
    public void editNotif(int idNotif, double price, boolean motionPrice) {
        Observable.just(coinDataBase)
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CoinDataBase>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(CoinDataBase coinDataBase) {
                        coinDataBase.getNotifDAO().editNitif(idNotif, price, motionPrice);
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


    private String getSortValueByBoolen(Boolean isSortAcs) {
        if (isSortAcs) {
            return "acs";
        } else {
            return "desc";
        }
    }
}
