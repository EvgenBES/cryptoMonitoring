package com.test.data.net;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.test.data.entity.CoinResponse;

import java.lang.reflect.Type;
import java.util.Map;

public class CoinJsonAdapter implements JsonDeserializer<CoinResponse>{

    @Override
    public CoinResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        JsonObject jsonObject = json.getAsJsonObject();

        CoinResponse coin = new CoinResponse();
        coin.setName("adssadas");
        coin.setPrice(123123213);
        coin.setId(123123213);

        return coin;
    }
}
