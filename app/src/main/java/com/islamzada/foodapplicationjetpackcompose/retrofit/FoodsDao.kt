package com.islamzada.foodapplicationjetpackcompose.retrofit

import com.islamzada.foodapplicationjetpackcompose.entity.FoodsMessage
import retrofit2.Call
import retrofit2.http.GET

interface FoodsDao {
    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods(): Call<FoodsMessage>
}