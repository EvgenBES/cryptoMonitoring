package com.test.data.net;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.data.entity.CoinResponse;
import com.test.data.entity.CoinResponces;
import com.test.data.entity.HttpError;
import com.test.data.entity.UserCoinResponse;
import com.test.domain.entity.Coin;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class RestService {

    private RestApi restApi;
    private Gson gson;
    private ErrorParserTransformer errorParserTransformer;

    @Inject
    public RestService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttp = new OkHttpClient
                .Builder()
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(logging)
                //debugging - отслеживаем изменения в сети (запросы)
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        gson = new GsonBuilder()
//                .setPrettyPrinting()
                .registerTypeAdapter(Coin.class, new CoinJsonAdapter())
                .create();

        this.restApi = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://api.coinmarketcap.com/v1/")
//                .baseUrl("https://api.backendless.com/7FD5B830-F360-99EB-FF4A-77DE676AB800/D4AECF17-A9CD-613E-FFAA-803DEBD1D700/")
                .client(okHttp)
                .build()
                .create(RestApi.class);

        errorParserTransformer = new ErrorParserTransformer(gson);
    }



    public Observable<List<CoinResponces>> getAllCoin() {
        return restApi
                .getAllCoin()
                .compose(errorParserTransformer.<List<CoinResponse>, HttpError>parseHttpError());
    }

//    public Observable<List<UserCoinResponse>> getCoin(String id) {
//        return restApi
//                .getCoin("id%3D" + id)
//                .compose(errorParserTransformer.<List<CoinResponse>, HttpError>parseHttpError());
//    }

    public Observable<List<CoinResponse>> addCoin(UserCoinResponse userCoinResponse) {
        return restApi
                .addCoin(userCoinResponse)
                .compose(errorParserTransformer.<List<CoinResponse>, HttpError>parseHttpError());
    }
}
