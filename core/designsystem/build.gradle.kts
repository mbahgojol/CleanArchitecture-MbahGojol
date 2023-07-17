import com.mbahgojol.convention.commonMain

plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.kotlin.multiplatform")
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    commonMain {
        dependencies {
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)
            implementation(compose.animation)
            implementation(compose.material3)
        }
    }
}

android {
    namespace = "com.mbahgojol.designsystem"
}
