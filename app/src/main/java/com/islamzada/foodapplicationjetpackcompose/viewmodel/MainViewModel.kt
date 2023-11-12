package com.islamzada.foodapplicationjetpackcompose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.islamzada.foodapplicationjetpackcompose.entity.Foods
import com.islamzada.foodapplicationjetpackcompose.repo.FoodsRepository

class MainViewModel : ViewModel() {
    var foodsList = MutableLiveData<List<Foods>>()
    var frepo = FoodsRepository()

    init {
        loadFoods()
        foodsList = frepo.getFoods()
    }

    fun loadFoods(){
        frepo.getAllFoods()
    }
}