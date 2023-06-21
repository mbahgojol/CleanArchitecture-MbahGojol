import com.android.build.api.dsl.LibraryExtension
import com.mbahgojol.convention.configureAndroidCompose
import com.mbahgojol.convention.configureBuildTypes
import com.mbahgojol.convention.configureDefaultConfig
import com.mbahgojol.convention.configureKotlin
import com.mbahgojol.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureDefaultConfig(this)
                configureBuildTypes(this)
                configureAndroidCompose(this)
                configureKotlin(this)
            }

            dependencies {
                add("implementation", platform(libs.findLibrary("compose-bom").get()))
                add("implementation", libs.findBundle("compose").get())
//                add("testImplementation", libs.findLibrary("junit").get())
//                add("androidTestImplementation", libs.findLibrary("androidJunit").get())
//                add("androidTestImplementation", libs.findLibrary("androidEspresso").get())
            }
        }
    }

}
