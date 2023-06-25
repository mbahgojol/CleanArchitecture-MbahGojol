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
    implementation(libs.androidx.paging.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}