package com.test.domain.usecases;

import com.test.domain.entity.Coin;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EditQuentityUseCase extends BaseUseCase {

    private CoinRepository coinRepository;

    @Inject
    public EditQuentityUseCase(CoinRepository coinRepository,
                               PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public void editCoin(Coin coin) {
     coinRepository
                .update(coin);

    }
}
