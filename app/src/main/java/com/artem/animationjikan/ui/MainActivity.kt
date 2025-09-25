package com.artem.animationjikan.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.artem.animationjikan.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.router.NavRoutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationJikanTheme {
                MyApp()
            }
        }
    }
}


@Composable
private fun MyApp() {
    MyNavHost {

    }
}

@Composable
fun MyNavHost(content: @Composable () -> Unit) {
    NavHost(
        navController = LocalNavScreenController.current,
        startDestination = NavRoutes.Home.router
    ) {
        composable(NavRoutes.Home.router) {

        }

        composable(NavRoutes.Like.router) {

        }

        composable(NavRoutes.Search.router) {

        }
    }
    CompositionLocalProvider() {
        content()
    }
}

val LocalNavScreenController = compositionLocalOf<NavHostController> {
    error("LocalNavScreenController not provided")
}

