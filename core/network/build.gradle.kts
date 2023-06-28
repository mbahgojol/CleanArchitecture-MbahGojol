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
    /*implementation("io.ktor:ktor-client-android:1.5.0")
    implementation("io.ktor:ktor-client-serialization:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("io.ktor:ktor-client-logging-jvm:1.5.0")*/

    implementation(libs.okhttp.loggingInterceptor)
    debugImplementation(libs.chucker.library)
    releaseImplementation(libs.chucker.library.no.op)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}