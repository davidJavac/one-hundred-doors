pipeline {
    agent any
    tools {
        jdk 'jdk 17'
        maven 'maven 3.9.0'
    }
    stages {
        stage("Checkout") {
               steps {
                    script {
                        def requestBody = env.REQUEST_BODY
                        def jsonSlurper = new groovy.json.JsonSlurperClassic()
                        def payload = jsonSlurper.parseText(requestBody)
                        def pullRequestHeadRef = payload.pull_request.head.ref

                        echo "variable pullRequestHeadRef ${pullRequestHeadRef}"
                        withEnv(["CHANGE_BRANCH=${pullRequestHeadRef}"]) {
                          git branch: "${env.CHANGE_BRANCH}", url: 'https://github.com/davidJavac/one-hundred-doors.git'
                        }
                    }

               }
          }

        stage("Compile") {
            steps {
                sh 'mvn compile'
            }
        }

        stage("Test") {
            steps {
                sh "mvn verify"
            }
        }

        stage("Code coverage") {
            steps {
                sh "mvn jacoco:report"
            }

            post {
                always {
                    publishHTML (target: [
                       reportDir: 'target/site/jacoco',
                       reportFiles: 'index.html',
                       reportName: "JaCoCo Report"
                    ])
                }
            }
        }

        stage("Static code analysis") {
            steps {
                sh "mvn checkstyle:checkstyle"
            }
        }
    }
}