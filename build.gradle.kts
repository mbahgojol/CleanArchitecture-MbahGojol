// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.cacheFixPlugin) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.gms.googleServices) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.perf) apply false
    alias(libs.plugins.hilt) apply false
    // please see for support library with ksp https://kotlinlang.org/docs/ksp-overview.html#supported-libraries
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.multiplatform) apply false
}

apply(from = File("gradle/dependencyGraph.gradle"))

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

allprojects {
    tasks.withType<com.android.build.gradle.internal.lint.AndroidLintTask> {
        tasks.findByName("kspTestKotlin")?.let {
            dependsOn(it)
        }
    }
    tasks.withType<com.android.build.gradle.internal.lint.AndroidLintAnalysisTask> {
        tasks.findByName("kspTestKotlin")?.let {
            dependsOn(it)
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
        compilerOptions {
            // Treat all Kotlin warnings as errors
            allWarningsAsErrors.set(true)

            // Enable experimental coroutines APIs, including Flow
            /*freeCompilerArgs.addAll(
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-opt-in=kotlinx.coroutines.FlowPreview",
            )*/
        }
    }
}
