package org.maktab.tamrinshaduing.retrofit;

import org.maktab.tamrinshaduing.retrofit.moodel.Response;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    //name api :: https://api.namefake.com/
    private static final String BASE_API_NAME = "https://api.namefake.com/";

    public static Retrofit getRetrifit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_API_NAME)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static String getName() {
        Retrofit retrofit = getRetrifit();
        Call<Response> call = retrofit.create(APIInterface.class).getRandom();
        Response response = null;
        try {
            response = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response!=null? response.getName(): "ERROR GET NAME";
    }
}
