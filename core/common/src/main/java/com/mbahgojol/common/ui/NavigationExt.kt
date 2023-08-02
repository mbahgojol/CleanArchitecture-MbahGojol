package com.mbahgojol.common.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val LocalNavigationProvider =
    staticCompositionLocalOf<NavHostController> { error("No navigation host controller provided.") }


@Composable
fun LocalNavigationContainer(content: @Composable (navHostController: NavHostController) -> Unit) {
    CompositionLocalProvider(LocalNavigationProvider provides rememberNavController()) {
        val navHostController = LocalNavigationProvider.current
        content.invoke(navHostController)
    }
}
