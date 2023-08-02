plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.compose")
    id("mbahgojol.android.library.jacoco")
    id("mbahgojol.android.ktor")
    id("mbahgojol.android.hilt")
}

android {
    namespace = "com.mbahgojol.core.common"
}

dependencies {
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)
    implementation(libs.gson)
    implementation(libs.timber)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}
