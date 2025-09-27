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
import com.artem.animationjikan.ui.screen.MainScreen
import com.artem.animationjikan.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.router.NavRoutes
/// 화면 관리자 역할
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
    
    /// NavHost : 화면 전환 관리자
    NavHost(
        navController = LocalNavScreenController.current,
        startDestination = NavRoutes.Home.router
    ) {
        /// Home 화면
        composable(NavRoutes.Home.router) {
            MainScreen()
        }

        /// 애니메이션 상세화면
        composable(NavRoutes.AnimationDetail.router) {

        }

        /// 만화 상세화면
        composable(NavRoutes.MangaDetail.router) {

        }

        /// 캐릭터 상세화면
        composable(NavRoutes.CharacterDetail.router) {

        }

        /// 성우 상세화면
        composable(NavRoutes.VoiceActorDetail.router) {

        }

    }
    CompositionLocalProvider() {
        content()
    }
}

val LocalNavScreenController = compositionLocalOf<NavHostController> {
    error("LocalNavScreenController not provided")
}

