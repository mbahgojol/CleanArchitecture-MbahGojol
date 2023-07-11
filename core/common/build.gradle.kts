plugins {
    kotlin("multiplatform")
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.compose")
    id("mbahgojol.android.library.jacoco")
    id("mbahgojol.android.ktor")
    id("mbahgojol.android.hilt")
}

android {
    namespace = "com.gojol.common"
}

dependencies {
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.analytics)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}
