import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

version = "2020.2"

project {

  params {
	// Disable editing of project and build settings from the UI to avoid issues with TeamCity
    param("teamcity.ui.settings.readOnly", "true")
  }
	
  buildType {
    id("HelloWorld")
    name = "Hello world"
    steps {
        script {
            scriptContent = "echo 'Hello world!'"
        }
    }
  }
}
