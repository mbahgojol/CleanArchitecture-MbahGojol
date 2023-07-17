import com.android.build.api.dsl.ApplicationExtension
import com.mbahgojol.convention.configureAndroid
import com.mbahgojol.convention.configureBuildTypes
import com.mbahgojol.convention.configureDefaultConfig
import com.mbahgojol.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.gradle.android.cache-fix")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroid()
                configureDefaultConfig(this)
                configureBuildTypes(this)

                dependencies {
                    add("implementation", platform(libs.findLibrary("kotlin.bom").get()))
                    add("implementation", libs.findLibrary("androidx.core").get())
                    add("implementation", libs.findLibrary("androidx.appcompat").get())
                    add("implementation", libs.findLibrary("google.android.material").get())
                    add(
                        "implementation",
                        libs.findLibrary("androidx.lifecycle.runtime.ktx").get(),
                    )
                }
            }
        }
    }

}
