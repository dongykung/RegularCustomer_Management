package com.dkproject.regularcustomermanagement.presentation.Component.Text

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun AnimatedStepText(
    text: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedContent(targetState = isSelected, label = "") { selected ->
        val color = if (selected) MaterialTheme.colorScheme.primary else Color.Gray
        Text(text, color = color)
    }
}