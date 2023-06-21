package com.mbahgojol.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

internal fun Project.configureBuildTypes(extension: ApplicationExtension) {
    with(extension) {
        val compileSdkVersion = libs.findVersion("androidCompileSdk").get().toString().toInt()
        compileSdk = compileSdkVersion
        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}

internal fun Project.configureBuildTypes(extension: LibraryExtension) {
    with(extension) {
        val compileSdkVersion = libs.findVersion("androidCompileSdk").get().toString().toInt()
        compileSdk = compileSdkVersion
        buildTypes {
            release {
                isMinifyEnabled = false
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
            val minSdkVersion = libs.findVersion("androidMinSdk").get().toString().toInt()
            val targetSdkVersion = libs.findVersion("androidTargetSdk").get().toString().toInt()

            minSdk = minSdkVersion
            targetSdk = targetSdkVersion
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
            val minSdkVersion = libs.findVersion("androidMinSdk").get().toString().toInt()
            val targetSdkVersion = libs.findVersion("androidTargetSdk").get().toString().toInt()

            minSdk = minSdkVersion
            targetSdk = targetSdkVersion
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")
            vectorDrawables {
                useSupportLibrary = true
            }
        }
    }
}
