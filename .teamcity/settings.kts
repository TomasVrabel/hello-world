import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

version = "2020.2"

project {

    params {
        // Disable editing of project and build settings from the UI to avoid issues with TeamCity
        param("teamcity.ui.settings.readOnly", "true")
    }

    vcsRoot (HelloWorldVcs)
    buildType(Build)
}

object Build : BuildType({
    name = "Hello world"

    vcs {
        root(HelloWorldVcs)
    }

    steps {
        script {
            name = "start"
            scriptContent = "echo 'Hello world!'"
        }
        dotnetBuild {
            name = "build"
            projects = "src/dotnet/HelloWorld"
        }
        script {
            name = "finish"
            scriptContent = "echo 'Build finished!'"
        }
    }

    triggers {
        vcs { }
    }
})

object HelloWorldVcs : GitVcsRoot({
    name = "HelloWorldVcs"
    url = "https://github.com/TomasVrabel/hello-world.git"
})