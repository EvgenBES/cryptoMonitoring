package com.test.data.net;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.test.domain.entity.Error;
import com.test.domain.entity.ErrorType;

import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

public class ErrorParserTransformer<S> {

    private Gson gson;

    public ErrorParserTransformer(Gson gson) {
        this.gson = gson;
    }

    public <T, E extends Throwable> ObservableTransformer<T, T> parseHttpError() {

        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {

                return upstream
                        .onErrorResumeNext(new Function<Throwable, ObservableSource<T>>() {
                            @Override
                            public ObservableSource<T> apply(Throwable throwable) throws Exception {

                                Error error;
                                if (throwable instanceof HttpException) {
                                    HttpException httpException = (HttpException) throwable;
                                    //FIXME Fix NPE error
                                    String errorBody = httpException.response().errorBody().string();
                                    E httpError = gson.fromJson(errorBody,
                                            new TypeToken<E>(){}.getType());

                                    //тут проверить параметры в HttpError и решить что делать дальше

                                    error = new Error(httpError.getMessage(), ErrorType.SERVER_ERROR);

                                } else if (throwable instanceof SocketTimeoutException) {
                                    error = new Error("Internet is not available",
                                            ErrorType.INTERNET_IS_NOT_AVAILABLE);
                                } else {
                                    error = new Error("Unexpected error",
                                            ErrorType.UNEXPECTED_ERROR);
                                }

                                return Observable.error(error);
                            }
                        });
            }
        };
    }
}
