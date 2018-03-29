package com.test.user.testzimadapplication.network;

import com.test.user.testzimadapplication.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetCatDogService {
    @GET("xim/api.php")
    Call<ApiResponse> getCatDogData(@Query("query") String cat);
}
