package com.test.domain.usecases;

import com.test.domain.entity.Coin;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import javax.inject.Inject;

public class AddNotifUseCase extends BaseUseCase {
    private CoinRepository coinRepository;

    @Inject
    public AddNotifUseCase(CoinRepository coinRepository,
                           PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public void addNotif(Coin coin) {
     coinRepository
                .addNotif(coin);

    }
}
