package com.islamzada.foodapplicationjetpackcompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.islamzada.foodapplicationjetpackcompose.entity.Foods
import com.islamzada.foodapplicationjetpackcompose.ui.theme.FoodApplicationJetpackComposeTheme
import com.islamzada.foodapplicationjetpackcompose.viewmodel.MainViewModel
import com.skydoves.landscapist.glide.GlideImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodApplicationJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainscreen"){
        composable("mainscreen") {
            MainScreen(navController = navController)
        }

        composable("detail_screen/{food}", arguments = listOf(
            navArgument("food"){type = NavType.StringType}

        )) {
            val json = it.arguments?.getString("food")
            val food = Gson().fromJson(json, Foods::class.java)
            DetailsScreen(food = food)
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val viewModel:MainViewModel = viewModel()
    val foodList = viewModel.foodsList.observeAsState(listOf())

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Foods") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = colorResource(id = R.color.color_1),
                    titleContentColor = colorResource(id = R.color.white)
                )
            )
        },
        content = {
                LazyColumn ( // Add padding to separate the content from the TopAppBar
                    contentPadding = PaddingValues(top = 70.dp)
                ) {
                    items(
                        count = foodList.value!!.count(),
                        itemContent = {
                            val food = foodList.value!![it]
                            Card(modifier = Modifier.padding(all = 5.dp).fillMaxWidth()) {
                                Row(modifier = Modifier.clickable {
                                    val foodJson = Gson().toJson(food)
                                    navController.navigate("detail_screen/$foodJson")
                                }) {
                                    Row(verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(all = 10.dp).fillMaxWidth()
                                    ) {
                                        GlideImage(
                                            imageModel = "http://kasimadalan.pe.hu/yemekler/resimler/${food.food_image}",
                                            modifier = Modifier.size(100.dp))
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Column(verticalArrangement = Arrangement.SpaceEvenly,
                                                modifier = Modifier.fillMaxHeight()
                                            ) {
                                                Text(text = food.food_name,fontSize = 20.sp)
                                                Spacer(modifier = Modifier.size(30.dp))
                                                Text(text = "${food.food_price} $",color = Color.Blue)
                                            }
                                            Icon(painter = painterResource(id = R.drawable.arrow_image),
                                                contentDescription = "")
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodApplicationJetpackComposeTheme {
    }
}