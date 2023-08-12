@file:Suppress("unused")

import com.android.build.api.dsl.LibraryExtension
import com.mbahgojol.convention.configureAndroid
import com.mbahgojol.convention.configureBuildTypes
import com.mbahgojol.convention.configureDefaultConfig
import com.mbahgojol.convention.configureFlavors
import com.mbahgojol.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("mbahgojol.kotlin.android")
                apply("org.gradle.android.cache-fix")
            }

            extensions.configure<LibraryExtension> {
                configureAndroid()
                configureDefaultConfig(this)
                configureBuildTypes(this)
                configureFlavors(this)

                dependencies {
                    add("implementation", libs.findLibrary("androidx.core").get())
                    add("implementation", libs.findLibrary("androidx.appcompat").get())
                    add("implementation", libs.findLibrary("google.android.material").get())
                }
            }
        }
    }
}
