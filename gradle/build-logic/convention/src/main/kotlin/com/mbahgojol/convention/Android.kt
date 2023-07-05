package com.mbahgojol.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

internal fun Project.configureBuildTypes(extension: ApplicationExtension) {
    with(extension) {
        compileSdk = libs.compileSdkVersion

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
        compileSdk = libs.compileSdkVersion

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
