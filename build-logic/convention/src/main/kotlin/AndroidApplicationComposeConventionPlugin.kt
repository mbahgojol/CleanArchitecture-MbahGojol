import com.android.build.api.dsl.ApplicationExtension
import com.mbahgojol.convention.configureAndroidCompose
import com.mbahgojol.convention.configureBuildTypes
import com.mbahgojol.convention.configureDefaultConfig
import com.mbahgojol.convention.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.jraska.module.graph.assertion")
            }

            extensions.configure<ApplicationExtension> {
                configureDefaultConfig(this)
                configureBuildTypes(this)
                configureAndroidCompose(this)
                configureKotlin(this)
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findBundle("compose").get())
                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidJunit").get())
                add("androidTestImplementation", libs.findLibrary("androidEspresso").get())
            }
        }
    }

}
