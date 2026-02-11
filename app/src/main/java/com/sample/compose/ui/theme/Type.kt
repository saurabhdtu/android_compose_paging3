package com.sample.compose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.sample.compose.R

val AppTypography = Typography(
    h1 = TextStyle(fontSize = 30.sp, fontFamily = FontFamily(Font(R.font.gladiora_extrablack))),
    h2 = TextStyle(fontSize = 28.sp, fontFamily = FontFamily(Font(R.font.gladiora_black))),
    h3 = TextStyle(fontSize = 26.sp, fontFamily = FontFamily(Font(R.font.gladiora_bold))),
    h4 = TextStyle(fontSize = 24.sp, fontFamily = FontFamily(Font(R.font.gladiora_semibold))),
    h5 = TextStyle(fontSize = 22.sp, fontFamily = FontFamily(Font(R.font.gladiora_medium))),
    h6 = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.gladiora_regular))),
    body1 = TextStyle(fontSize = 18.sp, fontFamily = FontFamily(Font(R.font.gladiora_thin))),
    body2 = TextStyle(fontSize = 16.sp, fontFamily = FontFamily(Font(R.font.gladiora_light))),
    subtitle1 = TextStyle(fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.gladiora_thin))),
    subtitle2 = TextStyle(fontSize = 12.sp, fontFamily = FontFamily(Font(R.font.gladiora_thin))),


    )
