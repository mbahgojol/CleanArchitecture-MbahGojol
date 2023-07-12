import com.mbahgojol.convention.commonMain

plugins {
    kotlin("multiplatform")
    id("mbahgojol.android.library")
    // todo implement compose yang support multiplatform
//    id("mbahgojol.android.library.compose")
}

kotlin {
    android()
    ios()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    commonMain {
        dependencies {

        }
    }
}

android {
    namespace = "com.mbahgojol.designsystem"
}
