plugins {
    `kotlin-dsl`
}

group = "com.mbahgojol.buildlogic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "mbahgojol.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidFeatureCompose") {
            id = "com.mbahgojol.buildlogic.convention.android.feature.compose"
            implementationClass = "AndroidFeatureComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.mbahgojol.buildlogic.convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("jvmLibrary") {
            id = "com.mbahgojol.buildlogic.convention.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}