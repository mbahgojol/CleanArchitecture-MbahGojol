import com.mbahgojol.convention.commonMain

plugins {
    kotlin("multiplatform")
    id("mbahgojol.android.library")
    alias(libs.plugins.composeMultiplatform)
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
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation(compose.animation)
            implementation(compose.material3)
            implementation(compose.material)
        }
    }
}

android {
    namespace = "com.mbahgojol.designsystem"
}
