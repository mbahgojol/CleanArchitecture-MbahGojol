@file:Suppress("UnstableApiUsage")

package com.mbahgojol.secrets_gradle_plugin

import com.android.build.api.variant.Variant
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.FileNotFoundException
import java.util.Properties

class SecretsPlugin : Plugin<Project> {

    private val extensionName = "secrets"

    override fun apply(project: Project) {
        val extension = project.extensions.create(
            extensionName, SecretsPluginExtension::class.java
        )
        val supportedComponents =
            listOf(project.androidAppComponent(), project.androidLibraryComponent())
        supportedComponents.forEach { component ->
            component?.onVariants { variant ->
                val defaultProperties = extension.defaultPropertiesFileName?.let {
                    project.rootProject.loadPropertiesFile(it)
                }

                val properties: Properties? = try {
                    project.rootProject.loadPropertiesFile(
                        extension.propertiesFileName
                    )
                } catch (e: FileNotFoundException) {
                    defaultProperties ?: throw e
                }
                generateConfigKey(project, extension, defaultProperties, properties, variant)
            }
        }
    }

    private fun generateConfigKey(
        project: Project,
        extension: SecretsPluginExtension,
        defaultProperties: Properties?,
        properties: Properties?,
        variant: Variant
    ) {
        // Inject defaults first
        defaultProperties?.let {
            variant.inject(it, extension.ignoreList)
        }

        properties?.let {
            variant.inject(it, extension.ignoreList)
        }

        // Inject build-type specific properties
        val buildTypeFileName = "${variant.buildType}.properties"
        val buildTypeProperties = try {
            project.rootProject.loadPropertiesFile(extension.path + buildTypeFileName)
        } catch (e: FileNotFoundException) {
            null
        }
        buildTypeProperties?.let {
            variant.inject(it, extension.ignoreList)
        }

        // Inject flavor-specific properties
        val flavorFileName = "${variant.flavorName}.properties"
        val flavorProperties = try {
            project.rootProject.loadPropertiesFile(extension.path + flavorFileName)
        } catch (e: FileNotFoundException) {
            null
        }
        flavorProperties?.let {
            variant.inject(it, extension.ignoreList)
        }
    }
}