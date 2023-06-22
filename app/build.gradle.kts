plugins {
    id("mbahgojol.android.application")
    id("mbahgojol.android.application.flavors")
    id("mbahgojol.android.application.compose")
    id("mbahgojol.android.hilt")
//    id("mbahgojol.android.application.firebase") // uncomment this line if used firebase (crashlytics, analytic, performance and etc)
}

android {
    namespace = "com.mbahgojol.cleanarchitecture"

    defaultConfig {
        applicationId = "com.mbahgojol.cleanarchitecture"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.androidx.lifecycle.runtime.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test)
    debugImplementation(libs.compose.ui.tooling)
    debugImplementation(libs.compose.ui.test.manifest)
}