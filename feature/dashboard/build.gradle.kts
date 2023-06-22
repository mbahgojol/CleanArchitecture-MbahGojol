plugins {
    id("mbahgojol.android.feature")
    id("mbahgojol.android.library.compose")
    id("mbahgojol.android.library.jacoco")
}

android {
    namespace = "com.mbahgojol.feature.dashboard"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}