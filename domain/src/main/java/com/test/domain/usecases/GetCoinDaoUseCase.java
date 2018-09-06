package com.test.domain.usecases;

import com.test.domain.entity.Coin;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class GetCoinDaoUseCase extends BaseUseCase {

    private CoinRepository coinRepository;

    @Inject
    public GetCoinDaoUseCase(CoinRepository coinRepository,
                             PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public Flowable<List<Coin>> getListCoinDao() {
        return coinRepository
                .getListCoinDao()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
