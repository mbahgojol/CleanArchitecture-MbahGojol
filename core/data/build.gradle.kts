plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.jacoco")
}

android {
    namespace = "com.mbahgojol.core.data"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.model)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}