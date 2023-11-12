package com.islamzada.foodapplicationjetpackcompose

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.islamzada.foodapplicationjetpackcompose.entity.Foods
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(food: Foods) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = food.food_name) },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.color_1),
                    titleContentColor = colorResource(id = R.color.white),
                )
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlideImage(
                    imageModel = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_image}",
                    modifier = Modifier.size(250.dp))
                Text(text = "${food.food_price} $",color = Color.Gray,fontSize = 50.sp)
                Button(onClick = {
                    Log.e("Food","${food.food_name} ordered.")
                },
                    modifier = Modifier.size(250.dp,50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.color_1),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Order")
                }
            }
        }
    )
}


