package com.example.filmo.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    // для навания фильма в его описании
    h1 = TextStyle(
        color = Color.White,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),

    // подзаголовок в описании фильма
    subtitle1 = TextStyle(
        color = Color.DarkGray,
        fontSize = 20.sp,
        fontWeight = FontWeight.Medium,
    ),

    // для навания подборки
    subtitle2 = TextStyle(
        color = Color.Gray,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
    ),

    // для навания фильма в его описании
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),

    // для имени актера
    h4 = TextStyle(
        fontSize = 10.sp
    ),

    // для роли актера
    h5 = TextStyle(
        color = Color.Gray,
        fontSize = 8.sp
    ),
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)