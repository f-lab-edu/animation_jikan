package com.artem.animationjikan.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artem.animationjikan.ui.components.BottomNavigationBar
import com.artem.animationjikan.ui.components.MainTab
import com.artem.animationjikan.ui.tab.HomeTab
import com.artem.animationjikan.ui.tab.LikeTab
import com.artem.animationjikan.ui.tab.SearchTab
import com.artem.animationjikan.ui.theme.AnimationJikanTheme


/// 앱 진입시 첫 화면 3개의 Tab을 가짐
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
        }
    )
}


@Composable
fun MainScreenContent(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = MainTab.Home.route,
    ) {

        composable(MainTab.Home.route) {
            HomeTab()
        }

        composable(MainTab.Like.route) {
            LikeTab()
        }

        composable(MainTab.Search.route) {
            SearchTab()
        }

    }


}

@Preview
@Composable
fun MainScreenPreview() {
    AnimationJikanTheme {
        MainScreen()
    }
}