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
        }
    }
}

android {
    namespace = "com.gojol.common"
}
