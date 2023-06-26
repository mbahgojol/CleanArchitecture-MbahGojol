package com.mbahgojol.secrets_gradle_plugin

open class SecretsPluginExtension {
    var propertiesFileName: String = defaultPropertiesFile
    var ignoreList: MutableList<String> = defaultIgnoreList
    var defaultPropertiesFileName: String? = null

    // folder of config your secrets
    var path: String = ""

    companion object {
        const val defaultPropertiesFile = "local.properties"
        val defaultIgnoreList = mutableListOf("sdk.dir")
    }
}