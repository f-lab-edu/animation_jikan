package com.artem.animationjikan.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.artem.animationjikan.util.enums.FilterType

@Composable
fun TypeChip(onClick: (FilterType) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(FilterType.entries) { item ->
            SuggestionChip(
                onClick = {
                    onClick(item)
                },
                label = {
                    Text(stringResource(item.stringRes))
                },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    containerColor = Color.Black,
                    labelColor = Color.White
                )
            )
        }
    }
}