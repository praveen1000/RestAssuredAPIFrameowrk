def mavenFile = "-f pom.xml"
def buildNumber = "-Djenkins.buildNumber=${env.BUILD_NUMBER}"

def setBuildStatus(String url, String commit, String state){
       step([$class: 'GitHubCommitStatusSetter',
            reposSource: [$class: "ManuallyEnteredRepositorySource", url: url],
            commitShaSource: [$class: "ManuallyEnteredShaSource", sha: commit],
            statusResultSource: [$class: 'ConditionalStatusResultSource',
                 results: [
                   [$class: 'BetterThanOrEqualBuildResult', result: 'SUCCESS', state: state, message: 'Build Passed'],
                   [$class: 'BetterThanOrEqualBuildResult', result: 'FAILURE', state: state, message: 'Build failed'],
                   [$class: 'AnyBuildResult', state: state, message: 'Loophole']
                 ]
            ]
       ])
}
pipeline{
      agent any

      tools {
            maven 'maven'
            jdk 'jdk'
      }
      triggers {
            pollSCM('')
      }
      environment{
            POM_GROUP_ID = readMavenPom().getGroupId()
            POM_ARTIFACT_ID = readMavenPom().getArtifactId()
            POM_VERSION = readMavenPom().getVersion()

      }
      /*parameters {
            string(name: 'COMMAND', defaultValue: 'mvn -f pom.xml', description: 'Command command')
      }*/
      stages{

            stage('Init'){
                steps{
                        echo "Group Id = ${POM_GROUP_ID}"
                        echo "ArtifactId = ${POM_ARTIFACT_ID}"
                        echo "Version = ${POM_VERSION}"

                        echo "PATH = ${PATH}"
                        echo "M2_HOME = ${M2_HOME}"
                        echo "JAVA_HOME = ${JAVA_HOME}"

                        echo "url  = ${env.GIT_URL}"
                        echo "commits = ${env.GIT_COMMIT}"
                        echo "branch= ${env.GIT_BRANCH}"
                }
            }

            stage('Clean and compile'){
                steps{
                        bat "mvn clean compile"
                }
            }
            stage('Test and verify'){

                steps{
                        bat "mvn test verify ${buildNumber}"
                }

                post{
                        always{
                                 cucumber buildStatus: 'UNSTABLE',
                                          fileIncludePattern: '**/*.json',
                                          trendsLimit: 10
                        }
                }
            }

      }
      post{
            always{
                echo "${currentBuild.result}"
                setBuildStatus("${env.GIT_URL}", "${env.GIT_COMMIT}", "${currentBuild.result}")
            }

      }
}
