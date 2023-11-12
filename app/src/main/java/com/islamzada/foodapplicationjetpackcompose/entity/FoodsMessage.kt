package com.islamzada.foodapplicationjetpackcompose.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsMessage(@SerializedName("yemekler")
                         @Expose
                         var foods:List<Foods>,
                         @SerializedName("success")
                         @Expose
                         var success:Int) {
}