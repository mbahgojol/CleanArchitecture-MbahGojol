package com.mbahgojol.cleanarchitecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mbahgojol.common.ui.LocalNavigationContainer
import com.mbahgojol.designsystem.theme.CleanArchitectureMbahGojolTheme
import com.mbahgojol.home.HomeScreen
import com.mbahgojol.home.detail.DetailScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalNavigationContainer { navHostController ->
                CleanArchitectureMbahGojolTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background,
                    ) {

                        NavHost(navController = navHostController, startDestination = "home") {
                            composable("home") {
                                HomeScreen()
                            }
                            composable("detail") {
                                DetailScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CleanArchitectureMbahGojolTheme {
        Greeting("Android")
    }
}
