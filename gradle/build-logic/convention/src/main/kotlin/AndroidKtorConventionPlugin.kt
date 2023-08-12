@file:Suppress("unused")

import com.android.build.gradle.LibraryExtension
import com.mbahgojol.convention.configureKtorClient
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidKtorConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureKtorClient(extension)
        }
    }

}
