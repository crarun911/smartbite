pluginManagement {
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

rootProject.name = "SmartBite"

include(
    ":backend",
    ":shared:core",
    ":shared:network",
    ":shared:data",
    ":shared:domain",
    ":shared:ui",
    ":shared:camera",
    ":apps:android"
)