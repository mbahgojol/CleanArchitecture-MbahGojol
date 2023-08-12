package com.mbahgojol.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getNews()
    }

    Scaffold {
        Column(Modifier.padding(it)) {
            Text(text = "Home Page")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HomeScreen()
}
