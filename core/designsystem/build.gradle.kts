plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.compose")
    id("mbahgojol.android.library.jacoco")
}

android {
    namespace = "com.mbahgojol.designsystem"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}