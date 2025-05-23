pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Client"

include(":appframework")
include(":apps:uiexample")
include(":apps:primaryapp")

project(":apps:uiexample").projectDir = file("apps/uiexample")
project(":apps:primaryapp").projectDir = file("apps/primaryapp")
project(":appframework").projectDir = file("appframework")
