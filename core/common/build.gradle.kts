plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.jacoco")
    id("mbahgojol.android.ktor")
}

android {
    namespace = "com.mbahgojol.core.common"
}

dependencies {

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}