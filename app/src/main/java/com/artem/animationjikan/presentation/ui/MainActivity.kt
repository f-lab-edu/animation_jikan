package com.artem.animationjikan.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.artem.animationjikan.presentation.ui.screen.MainScreen
import com.artem.animationjikan.presentation.ui.screen.detail.animation.AnimationDetailScreen
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.router.NavRoutes
import dagger.hilt.android.AndroidEntryPoint

/// 화면 관리자 역할
@AndroidEntryPoint
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
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavScreenController provides navController
    ) {
        MyNavHost()
    }
}

@Composable
fun MyNavHost() {

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
        composable(
            NavRoutes.AnimationDetail.router + "/{malId}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeIn(animationSpec = tween(durationMillis = 300))
            }, popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(durationMillis = 300)
                ) + fadeOut(animationSpec = tween(durationMillis = 300))
            }
        ) {
            AnimationDetailScreen()

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
}

val LocalNavScreenController = compositionLocalOf<NavHostController> {
    error("LocalNavScreenController not provided")
}

