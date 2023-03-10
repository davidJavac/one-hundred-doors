pipeline {
    agent any
    environment {
        BRANCH = ''
    }
    tools {
        jdk 'jdk 17'
        maven 'maven 3.9.0'
    }
    stages {
        stage("Checkout") {
               steps {
                  script {
                      echo "base branch ${env.BASE_BRANCH}, change branch ${env.CHANGE_BRANCH}"
                      if (env.ACTION == "closed" && env.MERGED == true) {
                          BRANCH = env.BASE_BRANCH
                      }
                      else {
                          BRANCH = env.CHANGE_BRANCH
                      }
                      echo "branch env ${env.BRANCH}"
                      git branch: "${env.BRANCH}", url: 'https://github.com/davidJavac/one-hundred-doors.git'
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