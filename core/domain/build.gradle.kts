plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.android.library.jacoco")
}

android {
    namespace = "com.mbahgojol.core.domain"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:model"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.espresso)
}