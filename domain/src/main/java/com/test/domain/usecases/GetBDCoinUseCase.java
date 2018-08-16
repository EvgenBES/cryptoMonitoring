package com.test.domain.usecases;

import com.test.domain.entity.Coin;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetBDCoinUseCase extends BaseUseCase {

    private CoinRepository coinRepository;

    @Inject
    public GetBDCoinUseCase(CoinRepository coinRepository,
                            PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public Flowable<List<Coin>> getBdCoin() {
        return coinRepository
                .getBdCoin()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
