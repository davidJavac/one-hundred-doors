pipeline {
    agent any
    tools {
        jdk 'jdk 17'
        maven 'maven 3.9.0'
    }
    stages {
        stage ("Checkout") {
               steps {
                  script {
                      def branch = ''
                      if (fileExists('.git')) {
                          if (env.ACTION == null && env.MERGED == null) {
                              def branchName = "awk '{print ${NF})}'"
                              branch = sh(returnStdout: true, script: "git branch --contains HEAD | grep -v 'HEAD detached' | '$branchName'").trim()
                          }
                          else if (env.ACTION == "closed" && env.MERGED == "true") {
                              branch = env.BASE_BRANCH
                          }
                          else {
                              branch = env.CHANGE_BRANCH
                          }
                          echo "base branch ${env.BASE_BRANCH}, change branch ${env.CHANGE_BRANCH}"
                          echo "action ${env.ACTION}, merged ${env.MERGED}"
                      }

                      echo "branch variable ${branch}"
                      
                      git branch: "${branch}", url: 'https://github.com/davidJavac/one-hundred-doors.git'
                  }

               }
          }

        stage ("Compile") {
            steps {
                sh 'mvn compile'
            }
        }

        stage ("Test") {
            steps {
                sh "mvn verify"
            }
        }

        stage ("Code coverage") {
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

        stage ("Static code analysis") {
            steps {
                sh "mvn checkstyle:checkstyle"
            }
        }

        stage ("Deploy") {
            steps {
                sh 'sudo pkill -f "one-hundred-doors-practice-1.0-SNAPSHOT.jar" || true'
                sh 'sudo nohup java -jar /var/lib/jenkins/workspace/one-hundred-doors-pipeline/target/one-hundred-doors-practice-1.0-SNAPSHOT.jar > /var/log/one-hundred-doors/one-hundred-doors.log 2>&1 &'
            }
        }
    }

    post {

         always {

              mail to: 'david.abramovich84@gmail.com',

              subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",

              body: "Your build completed, please check: ${env.BUILD_URL}"
              
              echo "build completed ${env.BUILD_URL}, pipeline ${currentBuild.fullDisplayName}"
         }

    }
}