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
        Map.Entry<String, JsonElement> entry = jsonObject.entrySet().iterator().next();
        JsonObject coinJsonObject = entry.getValue().getAsJsonObject();
        JsonElement coinElement = coinJsonObject.get("coin");
        if (coinElement.isJsonNull()) {
//            Coin coin = gson.fromJson(coinElement, Coin.class);
            return null;
        } else {
            throw new Error("dsfdsfsdf");
        }
    }
}
