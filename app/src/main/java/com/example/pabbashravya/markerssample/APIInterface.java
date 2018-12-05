package com.example.pabbashravya.markerssample;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("path/")
    Call<NetworkResponse> getLocationsList();
}
