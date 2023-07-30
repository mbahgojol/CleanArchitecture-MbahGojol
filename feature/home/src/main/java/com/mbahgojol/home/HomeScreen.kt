package com.mbahgojol.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mbahgojol.common.ui.addErrorListener
import com.mbahgojol.common.ui.addLoadingListener
import com.mbahgojol.common.ui.addSuccessListener
import com.mbahgojol.common.ui.collectAsUiStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }

    val listNewsUiState by viewModel.newsListState.collectAsUiStateWithLifecycle()

    Scaffold {
        Column(Modifier.padding(it)) {
            listNewsUiState.addLoadingListener {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                ) {
                    CircularProgressIndicator(
                        Modifier.align(Alignment.Center),
                    )
                }
            }

            listNewsUiState.addErrorListener { errorState ->
                Text(text = "Error = ${errorState.message}, Code = ${errorState.code}")
            }

            listNewsUiState.addSuccessListener { response ->
                Text(text = "Home Page = $response")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    HomeScreen()
}
