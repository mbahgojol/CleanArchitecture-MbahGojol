package com.mbahgojol.convention

import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.configure

fun Project.configureJava() {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }
}

private fun Project.java(action: JavaPluginExtension.() -> Unit) = extensions.configure(action)