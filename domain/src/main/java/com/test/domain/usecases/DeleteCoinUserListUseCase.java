package com.test.domain.usecases;

import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import javax.inject.Inject;

public class DeleteCoinUserListUseCase extends BaseUseCase {

    private CoinRepository coinRepository;

    @Inject
    public DeleteCoinUserListUseCase(CoinRepository coinRepository,
                                     PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public void deleteUserCoin(long coinId) {
     coinRepository
                .deleteUserCoin(coinId);
    }
}
