plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.jacoco")
    id("mbahgojol.android.ktor")
    id("mbahgojol.android.hilt")
    id("com.mbahgojol.secrets_gradle_plugin")
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.mbahgojol.core.network"
}

secrets {
    path = "config/"
}

dependencies {
    implementation(libs.okhttp.loggingInterceptor)
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}
