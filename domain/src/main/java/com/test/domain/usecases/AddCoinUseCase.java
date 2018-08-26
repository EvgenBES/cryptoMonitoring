package com.test.domain.usecases;

import com.test.domain.entity.Coin;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AddCoinUseCase extends BaseUseCase {

    private CoinRepository coinRepository;

    @Inject
    public AddCoinUseCase(CoinRepository coinRepository,
                          PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public void addCoin(Coin coin) {
        coinRepository
                .addUserCoin(coin);
    }
}
