package com.mbahgojol.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

fun Project.configureAndroid() {
    android {
        compileSdkVersion(libs.compileSdkVersion)

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11

            isCoreLibraryDesugaringEnabled = true
        }
    }

    dependencies {
        "coreLibraryDesugaring"(libs.findLibrary("tools.desugarjdklibs").get())
    }
}

private fun Project.android(action: BaseExtension.() -> Unit) = extensions.configure(action)

internal fun Project.configureBuildTypes(extension: ApplicationExtension) {
    with(extension) {
        val release = "release"

        signingConfigs {
            create(release) {
                keyAlias = "keystore_test"
                keyPassword = "123123"
                storePassword = "123123"
                storeFile = file("src/prod/keystore_test.jks")
            }
        }

        buildTypes {
            release {
                isShrinkResources = true
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
                )
                signingConfig = signingConfigs.getByName(release)
            }
        }
    }
}

internal fun Project.configureBuildTypes(extension: LibraryExtension) {
    with(extension) {
        buildTypes {
            release {
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        packaging {
            resources {
                excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            }
        }
    }
}

internal fun Project.configureDefaultConfig(extension: ApplicationExtension) {
    with(extension) {
        defaultConfig {
            minSdk = libs.minSdkVersion
            targetSdk = libs.targetSdkVersion

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            vectorDrawables {
                useSupportLibrary = true
            }
        }
    }
}

internal fun Project.configureDefaultConfig(extension: LibraryExtension) {
    with(extension) {
        defaultConfig {
            minSdk = libs.minSdkVersion

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
            vectorDrawables {
                useSupportLibrary = true
            }
        }
    }
}
