import com.mbahgojol.convention.androidMain

plugins {
    kotlin("multiplatform")
    id("mbahgojol.android.application")
    id("mbahgojol.android.application.compose")
    id("mbahgojol.android.application.flavors")
    id("mbahgojol.android.application.jacoco")
    id("mbahgojol.android.hilt")
}

kotlin {
    androidMain {
        dependencies {
            implementation(projects.presentation)
        }
    }
}

android {
    namespace = "com.myapplication"
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

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
