import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.schedule
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

version = "2021.2"

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

    artifactRules = "src/dotnet/HelloWorld/bin/Debug/netcoreapp3.1 => bin/"

    params {
        param("env.CLEANCITY", "1")
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
        vcs {
            enabled = true
        }

        schedule {
            enabled =  false
            schedulingPolicy = daily {
                hour = 7
            }
            branchFilter = ""
            triggerBuild = always()
        }
    }
})

object HelloWorldVcs : GitVcsRoot({
    name = "HelloWorldVcs"
    url = "https://github.com/TomasVrabel/hello-world.git"
	branch = "refs/heads/master"
	branchSpec = """
            +:refs/heads/*
        """.trimIndent()
})
