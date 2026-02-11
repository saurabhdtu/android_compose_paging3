package com.sample.compose.ui.common

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sample.compose.R


@Composable
fun LottieAnim(modifier: Modifier = Modifier, @RawRes resId: Int) {
    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(resId = resId))

    val preLoader by animateLottieCompositionAsState(
        lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = lottieComposition,
        modifier = modifier,
        progress = preLoader
    )
}