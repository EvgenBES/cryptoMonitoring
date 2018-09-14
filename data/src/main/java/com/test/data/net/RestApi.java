package com.test.data.net;

import com.test.data.entity.CoinRequest;
import com.test.data.entity.CoinResponse;
import com.test.data.entity.UserCoinResponse;
import com.test.data.model.Currency;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RestApi {

    @GET("ticker")
    Flowable<Currency> getAllCoin(@Query("convert") String mBase,
                                  @Query("limit")Integer mLimit,
                                  @Query("sort")String mSort,
                                  @Query("structure")String mStructure);

    @GET("data/coin/search")
    Observable<List<CoinResponse>> searchCoin(@Query("symbol") String name);


}
