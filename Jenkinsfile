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
                      echo "base branch ${env.BASE_BRANCH}, change branch ${env.CHANGE_BRANCH}"
                      echo "action ${env.ACTION}, merged ${env.MERGED}"
                      def branch = ''
                      if (env.ACTION == "closed" && env.MERGED == true) {
                          branch = env.BASE_BRANCH
                      }
                      else {
                          branch = env.CHANGE_BRANCH
                      }
                      echo "branch variable ${branch}"
                      
                      git branch: "${branch}", url: 'https://github.com/davidJavac/one-hundred-doors.git'
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