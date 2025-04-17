package com.microserge.pddanswers.ui.presentation.question_list.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ImageWithSkeleton(url: String) {
    var isLoading by remember { mutableStateOf(true) }
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .listener(
                onStart = { isLoading = true },
                onSuccess = {_,_ -> isLoading = false},
                onCancel = {isLoading = false},
                onError = {_,_ -> isLoading = false}
            ).build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier.height(100.dp).width(100.dp))

    if (isLoading) {
        ShimmerSkeleton()
    }
}

@Composable
fun ShimmerSkeleton() {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.LightGray.copy(alpha = 0.7f),
                        Color.Gray.copy(alpha = alpha.value),
                        Color.LightGray.copy(alpha = 0.7f)
                    )
                )
            )
    )
}