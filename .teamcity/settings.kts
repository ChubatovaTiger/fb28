import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

version = "2023.11"

project {

    subProject(Project21)
}


object Project21 : Project({
    name = "project21"
vcsRoot(Project21_Repo3)
    buildType(Project21_Build1)
 buildType(Project21_Build2)
})

object Project21_Build1 : BuildType({
    name = "build1"

    vcs {
        root(DslContext.settingsRoot)
root(Project21_Repo3, "+:.=>repo3")

    }
    dependencies {
        snapshot(Project21_Build2) {
            reuseBuilds = ReuseBuilds.NO
        }
    }
    steps {
        script {
            id = "simpleRunner"
            scriptContent = "ls repo3"
        }
    }
})

object Project21_Build2 : BuildType({
    name = "build2"

    vcs {
        root(DslContext.settingsRoot)
root(Project21_Repo3, "+:.=>repo3")
    }
    requirements {
        contains("teamcity.agent.name", "mm")
    }
})



object Project21_Repo3 : GitVcsRoot({
    name = "repo3"
    url = "git@github.com:ChubatovaTiger/repo3.git"
    branch = "refs/heads/main"
    authMethod = uploadedKey {
        uploadedKey = "rsanopwd4"
    }
})
