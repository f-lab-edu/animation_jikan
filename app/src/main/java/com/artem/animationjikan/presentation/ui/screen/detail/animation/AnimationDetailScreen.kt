package com.artem.animationjikan.presentation.ui.screen.detail.animation

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.provider.CalendarContract
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import com.artem.animationjikan.R
import com.artem.animationjikan.presentation.ui.LocalNavScreenController
import com.artem.animationjikan.presentation.ui.theme.AnimationJikanTheme
import com.artem.animationjikan.util.enums.DetailTabs
import kotlinx.coroutines.selects.select


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimationDetailScreen(
    viewModel: AnimationDetailViewModel = hiltViewModel()
) {
    val navController = LocalNavScreenController.current

    Scaffold(
        containerColor = colorResource(R.color.TransparencyBlack),
        content = {
            Column {
                AnimationDetailTopBar(
                    onBackPressed = { navController.popBackStack() },
                    onFavoriteClick = { viewModel }
                )
                AnimationDetailContent()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationDetailTopBar(
    onBackPressed: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    TopAppBar(
        title = {},
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = colorResource(R.color.white),
            actionIconContentColor = colorResource(R.color.white)
        ),
        navigationIcon = {
            IconButton(
                onClick = { onBackPressed() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onFavoriteClick() }) {
                Icon(
                    painter = painterResource(R.drawable.ic_favorite_off),
                    contentDescription = null
                )
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AnimationDetailContent() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            //.background(color = colorResource(R.color.black))
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2.5f)
                .background(color = colorResource(R.color.purple_200))
        ) {

        }

        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            text = "Sample Animation Title",
            color = colorResource(R.color.white),
            fontSize = 18.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(600)
        )

        Row(
            modifier = Modifier.padding(all = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_star_full),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                "4.8",
                fontSize = 14.sp,
                lineHeight = 18.sp,
                fontWeight = FontWeight(400),
                color = colorResource(R.color.white)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableText(
            fullText = "The contents of a hidden grave draw the interest of an industrial titan and send officer K, an LAPD blade runner, on a quest to find a missing legend. The contents of a hidden grave draw the interest of an industrial titan and send officer K, an LAPD blade runner, on a quest to find a missing legend.",
        )

        Spacer(modifier = Modifier.height(11.dp))

        TabBars()

    }
}

@Composable
fun TabBars() {
    val selectedDestination = remember { mutableStateOf(DetailTabs.FIRST) }
    val navController = rememberNavController()


    PrimaryTabRow(
        selectedTabIndex = 0,
        containerColor = Color.Transparent,
        contentColor = colorResource(R.color.white),
        divider = {
            Spacer(modifier = Modifier.height(0.dp))
        },
        indicator = {
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(selectedDestination.value.ordinal)
                    .height(4.dp)
                    .padding(horizontal = 20.dp)
                    .background(
                        color = colorResource(
                            id = R.color.red
                        )
                    )
            )
        }
    ) {
        Tab(
            selected = selectedDestination.value == DetailTabs.FIRST,
            selectedContentColor = colorResource(R.color.white),
            unselectedContentColor = colorResource(R.color.white),
            onClick = {
                /*navController.navigate(route = destination.route)
                selectedDestination = index*/
                selectedDestination.value = DetailTabs.FIRST
            },
            text = {
                Text(
                    text = "애피소드",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )

        Tab(
            selected = selectedDestination.value == DetailTabs.SECOND,
            onClick = {
                /*navController.navigate(route = destination.route)
                selectedDestination = index*/
                selectedDestination.value = DetailTabs.SECOND
            },
            text = {
                Text(
                    text = "리뷰",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )

        Tab(
            selected = selectedDestination.value == DetailTabs.THIRD,
            onClick = {
                /*navController.navigate(route = destination.route)
                selectedDestination = index*/
                selectedDestination.value = DetailTabs.THIRD
            },
            text = {
                Text(
                    text = "캐릭터",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )
    }
}

@Composable
fun ExpandableText(
    fullText: String,
    minimizedMaxLines: Int = 3
) {
    var expanded by remember { mutableStateOf(false) }

    val maxLines = if (expanded) Int.MAX_VALUE else minimizedMaxLines

    val toggleExpanded: () -> Unit = { expanded = !expanded }

    var isTextClipped by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(
            text = fullText,
            maxLines = maxLines,
            // 텍스트가 잘렸는지 확인하는 로직은 onTextLayout으로 유지
            onTextLayout = { textLayoutResult ->
                // ... isTextClipped 상태 업데이트 로직 ...
                if (!expanded) {
                    // 텍스트가 잘렸는지 여부를 저장합니다.
                    isTextClipped = textLayoutResult.hasVisualOverflow
                }
            },
            modifier = Modifier.clickable(onClick = {
                // 클릭 시 확장/축소 로직 실행
                toggleExpanded()
            }),
            color = Color.White
        )

        if (isTextClipped || expanded) {
            Text(
                text = if (expanded) " 접기" else "...more",
                modifier = Modifier.clickable(onClick = toggleExpanded),
                color = colorResource(R.color.TransparencyWhite),
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
@Preview
fun ExpandableTextPreview() {
    val longText =
        "Jetpack Compose는 선언적 UI 프레임워크로, Kotlin을 사용하여 UI를 구축합니다. 이 예시는 긴 텍스트를 일부만 보여주고 사용자의 클릭에 반응하여 전체 내용을 확장하는 기능을 구현합니다. 긴 텍스트를 다룰 때 성능과 가독성을 모두 고려하는 것이 중요합니다."

    Column(modifier = Modifier.padding(16.dp)) {
        ExpandableText(
            fullText = longText,
            minimizedMaxLines = 3 // 초기 3줄로 제한
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text("다른 콘텐츠")
    }
}


@Composable
@Preview
fun AnimationDetailPreview() {
    AnimationJikanTheme {
        AnimationDetailScreen()
    }
}

@Composable
@Preview
fun AnimationDetailTopBarPreview() {
    AnimationJikanTheme {
        AnimationDetailTopBar(onBackPressed = {}, onFavoriteClick = {})
    }
}
