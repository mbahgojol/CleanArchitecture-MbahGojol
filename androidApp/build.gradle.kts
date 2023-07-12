plugins {
    id("mbahgojol.android.application")
    id("mbahgojol.kotlin.android")
    id("mbahgojol.android.application.compose")
    id("mbahgojol.android.application.flavors")
    alias(libs.plugins.ksp)
    alias(libs.plugins.composeMultiplatform)
}

dependencies {
    implementation(libs.androidx.activity.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlin.coroutines.android)
    implementation(projects.presentation)
}

android {
    namespace = "com.myapplication"

    kotlinOptions {
        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=kotlinx.coroutines.FlowPreview",
        )
    }
}
