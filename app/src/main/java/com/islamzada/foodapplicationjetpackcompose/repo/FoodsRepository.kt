package com.islamzada.foodapplicationjetpackcompose.repo

import androidx.lifecycle.MutableLiveData
import com.islamzada.foodapplicationjetpackcompose.entity.Foods
import com.islamzada.foodapplicationjetpackcompose.entity.FoodsMessage
import com.islamzada.foodapplicationjetpackcompose.retrofit.ApiUtils
import com.islamzada.foodapplicationjetpackcompose.retrofit.FoodsDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsRepository {
    var foodsList = MutableLiveData<List<Foods>>()
    var foodsDao: FoodsDao

    init {
        foodsDao = ApiUtils.getFoodsDao()
        foodsList = MutableLiveData()
    }

    fun getFoods():MutableLiveData<List<Foods>> {
        return foodsList
    }

    fun getAllFoods(){
        foodsDao.allFoods().enqueue(object : Callback<FoodsMessage>{
            override fun onResponse(call: Call<FoodsMessage>, response: Response<FoodsMessage>) {
                val list = response.body()?.foods
                foodsList.value = list
            }
            override fun onFailure(call: Call<FoodsMessage>?, t: Throwable?) {}
        })
    }
}
