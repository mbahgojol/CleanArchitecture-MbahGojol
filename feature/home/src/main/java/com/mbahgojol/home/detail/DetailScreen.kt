package com.mbahgojol.home.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun DetailScreen() {
    Timber.e("Detail Screen")

    Scaffold(Modifier.fillMaxSize()) {
        Box(Modifier.padding(it)) {
            Text(
                text = "Detail Screen",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DetailScreen()
}
