import jetbrains.buildServer.configs.kotlin.v2019_2.*

version = "2020.2"

project {
    params {
		// Disable editing of project and build settings from the UI to avoid issues with TeamCity
        param("teamcity.ui.settings.readOnly", "true")
    }
}