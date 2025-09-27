package com.artem.animationjikan.util.router

sealed class NavRoutes(val router: String) {
    data object Home : NavRoutes("home")
    data object Like : NavRoutes("like")
    data object Search : NavRoutes("search")
}