package com.artem.animationjikan.ui.screen

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import com.artem.animationjikan.ui.tab.home.HomeTab
import com.artem.animationjikan.ui.tab.like.LikeTab
import com.artem.animationjikan.ui.tab.search.SearchTab
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
        modifier = modifier
    ) {

        composable(route = MainTab.Home.route) {
            HomeTab()
        }

        composable(route = MainTab.Like.route) {
            LikeTab()
        }

        composable(route = MainTab.Search.route) {
            SearchTab()
        }

    }


}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview
@Composable
fun MainScreenPreview() {
    AnimationJikanTheme {
        MainScreen()
    }
}