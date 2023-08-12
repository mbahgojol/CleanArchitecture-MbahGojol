@file:Suppress("unused")

import com.mbahgojol.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("mbahgojol.android.library")
                apply("mbahgojol.kotlin.android")
                apply("mbahgojol.android.hilt")
            }

            dependencies {
                add("implementation", libs.findLibrary("timber").get())
                add("implementation", libs.findLibrary("androidx.core").get())
                add("implementation", libs.findLibrary("androidx.appcompat").get())
                add("implementation", libs.findLibrary("google.android.material").get())

                add("implementation", project(":core:designsystem"))

                add("implementation", libs.findLibrary("coil.coil").get())
                add("implementation", libs.findLibrary("coil.compose").get())
                add("implementation", libs.findLibrary("coil.gif").get())

                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
                add("implementation", libs.findLibrary("androidx.constraintlayout.compose").get())

                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
                add("implementation", libs.findLibrary("kotlinx.coroutines.core").get())
                add("implementation", platform(libs.findLibrary("kotlin.bom").get()))

                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.test.junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.test.espresso").get())
            }
        }
    }

}
