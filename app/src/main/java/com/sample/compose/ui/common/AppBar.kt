package com.sample.compose.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@Composable
fun MyAppBar(title: String?, onBackIconPressed: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title ?: "",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                fontSize = TextUnit(
                    22f,
                    TextUnitType.Sp
                )
            )
        },

        navigationIcon = {
            IconButton(onClick = onBackIconPressed) {

            }
        }
    )
}