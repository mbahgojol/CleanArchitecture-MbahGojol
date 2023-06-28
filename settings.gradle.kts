pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "CleanArchitecture-MbahGojol"
include (":app")
include(":core")
include(":core:designsystem")
include(":core:domain")
include(":core:data")
include(":core:network")
include(":core:common")
include(":core:database")
include(":core:model")
include(":feature")
include(":feature:home")
