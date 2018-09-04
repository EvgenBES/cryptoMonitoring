package com.test.domain.usecases;

import com.test.domain.entity.Coin;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class SearchCoinLocalUseCase extends BaseUseCase {

    private CoinRepository coinRepository;

    @Inject
    public SearchCoinLocalUseCase(CoinRepository coinRepository,
                                  PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public Flowable<List<Coin>> searchCoin(String searchName) {
        return coinRepository
                .search(searchName)
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
