plugins {
    id("mbahgojol.android.application")
    id("mbahgojol.android.application.flavors")
    id("mbahgojol.android.application.compose")
    id("mbahgojol.android.application.jacoco")
    id("mbahgojol.android.hilt")
//    id("mbahgojol.android.test")
    id("mbahgojol.android.application.firebase")
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.mbahgojol.cleanarchitecture"

    defaultConfig {
        applicationId = "com.mbahgojol.cleanarchitecture"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(projects.feature.dashboard)
    implementation(projects.core.common)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}