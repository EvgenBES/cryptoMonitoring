package com.test.domain.usecases;

import com.test.domain.entity.Coin;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;

public class GetListCoinUseCase extends BaseUseCase {

    private CoinRepository coinRepository;

    @Inject
    public GetListCoinUseCase(CoinRepository coinRepository,
                              PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public Flowable<List<Coin>> getCoins() {
        return coinRepository
                .getAll()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
