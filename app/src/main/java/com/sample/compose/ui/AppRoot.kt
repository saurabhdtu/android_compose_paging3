package com.sample.compose.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sample.compose.ui.common.navigation.AppNavHost

@Composable
fun AppRoot() {
    BoxWithConstraints {
        val contentPadding = when {
            maxWidth < 600.dp -> PaddingValues(0.dp)
            maxWidth < 840.dp -> PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            else -> PaddingValues(horizontal = 24.dp, vertical = 12.dp)
        }

        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(contentPadding)
            ) {
                AppNavHost()
            }
        }
    }
}
