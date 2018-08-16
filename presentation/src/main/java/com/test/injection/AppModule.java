package com.test.injection;

import android.content.Context;

import com.test.data.db.CoinDataBase;
import com.test.data.repositories.CoinRepositoryImpl;
import com.test.domain.executors.PostExecutionThread;
import com.test.domain.repositories.CoinRepository;
import com.test.executor.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return context;
    }


    @Provides
    public static CoinRepository provideCoinRepository(CoinRepositoryImpl coinRepository) {
        return coinRepository;
    }

    @Singleton
    @Provides
    public static PostExecutionThread provideUIThread(UIThread uiThread) {
        return uiThread;
    }

    @Singleton
    @Provides
    public static CoinDataBase provideCoinDataBase(Context context) {
        return CoinDataBase.getInstance(context);
    }



}
