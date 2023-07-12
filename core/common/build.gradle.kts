import com.mbahgojol.convention.commonMain

plugins {
    kotlin("multiplatform")
    id("mbahgojol.android.library")
    alias(libs.plugins.composeMultiplatform)
    // todo implement compose yang support multiplatform
//    id("mbahgojol.android.library.compose")
//    id("mbahgojol.android.library.jacoco")
//    id("mbahgojol.android.ktor")
//    id("mbahgojol.android.hilt")
}

kotlin {
    android()
    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    commonMain {
        dependencies {
            implementation(compose.ui)
        }
    }
}

android {
    namespace = "com.gojol.common"
}
