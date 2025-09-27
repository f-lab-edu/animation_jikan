package com.artem.animationjikan.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.artem.animationjikan.R
import com.artem.animationjikan.ui.theme.AnimationJikanTheme

sealed class MainTab(
    val iconOn: Int,
    val iconOff: Int,
    val label : String,
    val route : String
) {
    data object Home : MainTab(
        iconOn = R.drawable.ic_home_on,
        iconOff = R.drawable.ic_home_off,
        label = "홈",
        route = "home"
    )

    data object Like : MainTab(
        iconOn = R.drawable.ic_favorite_on,
        iconOff = R.drawable.ic_favorite_off,
        label = "보관함",
        route = "like"
    )

    data object Search : MainTab(
        iconOn = R.drawable.ic_search_on,
        iconOff = R.drawable.ic_search_off,
        label = "검색",
        route = "search"
    )

}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val buttons = listOf(
        BottomNavItem.Home,
        BottomNavItem.Like,
        BottomNavItem.Search,
    )

    /*val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route*/

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            buttons.forEachIndexed { _, item ->
                Box(Modifier.clickable {
                    navController.navigate(item.route) {
                        //Back Stack 초기화 후 화면이동
                        popUpTo(id = navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // 단일 인스턴스 실행
                        launchSingleTop = true

                        // 저장된 상태 복원
                        restoreState = true

                    }
                }) {
                    BottomNavigationBarItem(
                        isSelected = false,
                        iconOn = item.iconOn,
                        iconOff = item.iconOff,
                        label = item.label,
                    )
                }
            }
        }
    }

}


@Composable
fun BottomNavigationBarItem(
    isSelected: Boolean,
    iconOn: Int,
    iconOff: Int,
    label: String
) {
    Column(
        modifier = Modifier.size(56.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val icon = if (isSelected) iconOn else iconOff

        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )

        Text(
            label,
            style = TextStyle(
                fontSize = 8.sp
            ),
            color = if (isSelected) Color(0xFF2F44AC) else Color(0xFFACACAC)
        )
    }
}

@Composable
@Preview
fun BottomNavigationBarItemPreview() {
    var state by remember {
        mutableStateOf(false)
    }

    val button = BottomNavItem.Home

    AnimationJikanTheme {
        Box(modifier = Modifier.clickable {
            state = !state
        }) {
            BottomNavigationBarItem(
                isSelected = state,
                iconOn = button.iconOn,
                iconOff = button.iconOff,
                label = button.label
            )
        }
    }
}

sealed class BottomNavItem(
    var iconOff: Int,
    var label: String,
    var route: String,
    var iconOn: Int,
) {
    data object Home : BottomNavItem(
        iconOn = R.drawable.ic_home_on,
        iconOff = R.drawable.ic_home_off,
        label = "홈",
        route = "home"
    )

    data object Like : BottomNavItem(
        iconOn = R.drawable.ic_favorite_on,
        iconOff = R.drawable.ic_favorite_off,
        label = "보관함",
        route = "like"
    )

    data object Search : BottomNavItem(
        iconOn = R.drawable.ic_search_on,
        iconOff = R.drawable.ic_search_off,
        label = "검색",
        route = "search"
    )
}