package org.maktab.tamrinshaduing.retrofit;

import org.maktab.tamrinshaduing.retrofit.moodel.Response;

import retrofit2.Call;

import retrofit2.http.GET;

public interface APIInterface {
    @GET(".")
    Call<Response> getRandom();
}
