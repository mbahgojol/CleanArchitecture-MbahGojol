plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.jacoco")
}

android {
    namespace = "com.mbahgojol.core.model"
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}