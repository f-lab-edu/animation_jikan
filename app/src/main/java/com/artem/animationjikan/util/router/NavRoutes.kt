package com.artem.animationjikan.util.router

sealed class NavRoutes(val router: String) {
    data object Home : NavRoutes("Home")
    data object AnimationDetail : NavRoutes("animationDetail")
    data object MangaDetail : NavRoutes("MangaDetail")
    data object CharacterDetail : NavRoutes("CharacterDetail")
    data object VoiceActorDetail : NavRoutes("VoiceActorDetail")
    data object Search : NavRoutes("Search")
}