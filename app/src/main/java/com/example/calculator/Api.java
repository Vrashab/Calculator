package com.example.calculator;

import com.google.firebase.firestore.auth.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    @POST("/upload/calculator.json")
    Call<Values> setData( @Body Values user);

    @GET("/upload/calculator")
    Call<Map<String,Values>> getData();
}
