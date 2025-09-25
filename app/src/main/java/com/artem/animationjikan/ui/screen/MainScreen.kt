package com.artem.animationjikan.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.artem.animationjikan.ui.components.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val tabNavController = rememberNavController()

    Scaffold(
        content = {
            MainScreenContent(navController = tabNavController, modifier = Modifier.padding(it))
        },
        bottomBar = {
            BottomNavigationBar(tabNavController)
        })
}


@Composable
fun MainScreenContent(navController: NavController, modifier: Modifier) {

}