package com.mbahgojol.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

val VersionCatalog.compileSdkVersion
    get() = findVersion("androidCompileSdk").get().toString().toInt()

val VersionCatalog.minSdkVersion get() = findVersion("androidMinSdk").get().toString().toInt()
val VersionCatalog.targetSdkVersion get() = findVersion("androidTargetSdk").get().toString().toInt()
