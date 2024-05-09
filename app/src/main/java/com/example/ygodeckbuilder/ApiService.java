package com.example.ygodeckbuilder;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("cardinfo.php")
    Call<CardInfoResponse> getCardInfo(@Query("name") String cardName);
}
