import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.script

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2023.11"

project {

    buildType(Project2_Sample)

    template(Project2_Project2_Tmpl1)

    subProject(Project2_Project2_Project21)
}

object Project2_Sample : BuildType({
    templates(Project2_Project2_Tmpl1)
    id = AbsoluteId("Project2_Sample")
    name = "sample"
})

object Project2_Project2_Tmpl1 : Template({
    id("Tmpl1")
    name = "tmpl1"

    params {
        param("parsample", "parsamplevalue")
    }

    steps {
        script {
            id = "simpleRunner"
            scriptContent = "echo %parsample%"
        }
    }
})


object Project2_Project2_Project21 : Project({
    id("Project21")
    name = "project21"

    buildType(Project2_Project2_Project21_Build1)
})

object Project2_Project2_Project21_Build1 : BuildType({
    id("Project21_Build1")
    name = "build1"

    vcs {
        root(DslContext.settingsRoot)
    }
})
