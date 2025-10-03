package com.artem.animationjikan.ui.tab.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.artem.animationjikan.R
import com.artem.animationjikan.ui.MyNavHost
import com.artem.animationjikan.ui.components.BottomNavigationBar
import com.artem.animationjikan.ui.screen.MainScreenContent
import com.artem.animationjikan.ui.theme.AnimationJikanTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchTab() {

}


@Composable
@Preview
fun SearchPreView() {
    AnimationJikanTheme {
        SearchTab()
    }
}