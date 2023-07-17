import com.mbahgojol.convention.commonMain

plugins {
    id("mbahgojol.android.library")
    id("mbahgojol.kotlin.multiplatform")
    alias(libs.plugins.composeMultiplatform)
    // todo implement compose yang support multiplatform
//    id("mbahgojol.android.library.compose")
//    id("mbahgojol.android.library.jacoco")
//    id("mbahgojol.android.ktor")
//    id("mbahgojol.android.hilt")
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
