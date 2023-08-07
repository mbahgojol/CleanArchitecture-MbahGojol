package com.mbahgojol.home.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mbahgojol.common.ui.LocalNavigationProvider

@Composable
fun DetailScreen() {
    val navController = LocalNavigationProvider.current
    Scaffold(Modifier.fillMaxSize()) {
        Column(Modifier.padding(it)) {
            Text(
                text = "Detail Screen",
            )
        }
        Button(
            onClick = {
                navController.navigateUp()
            },
        ) {
            Text(text = "back")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    DetailScreen()
}
