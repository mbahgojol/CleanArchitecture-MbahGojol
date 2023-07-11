package com.mbahgojol.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun Project.configureKotlin() {
    configureJava()
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}


fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.androidMain(block: KotlinSourceSet.() -> Unit) =
    sourceSets.getByName("androidMain", block)

fun org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension.commonMain(block: KotlinSourceSet.() -> Unit) =
    sourceSets.getByName("commonMain", block)
