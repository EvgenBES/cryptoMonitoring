package com.test.domain.usecases;

import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;

import javax.inject.Inject;

public class EditNotifUseCase extends BaseUseCase {
    private CoinRepository coinRepository;

    @Inject
    public EditNotifUseCase(CoinRepository coinRepository,
                            PostExecutionThread postExecutionThread) {
        super(postExecutionThread);
        this.coinRepository = coinRepository;
    }

    public void editNotif(int idNotif, double price, boolean morionPrice) {
        coinRepository
                .editNotif(idNotif, price, morionPrice);

    }
}
