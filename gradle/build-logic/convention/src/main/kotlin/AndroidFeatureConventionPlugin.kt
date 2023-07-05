import com.android.build.api.dsl.LibraryExtension
import com.mbahgojol.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("mbahgojol.android.library")
                apply("mbahgojol.android.hilt")
            }

            extensions.configure<LibraryExtension> {

            }

            dependencies {
                add("implementation", project(":core:model"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:data"))
                add("implementation", project(":core:common"))
                add("implementation", project(":core:domain"))

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
