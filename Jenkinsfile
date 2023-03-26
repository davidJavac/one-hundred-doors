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
                      echo "base branch ${env.BASE_BRANCH}, change branch ${env.CHANGE_BRANCH}"
                      echo "action ${env.ACTION}, merged ${env.MERGED}"
                      def branch = ''
                      if (env.ACTION == "closed" && env.MERGED == "true") {
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
                script {
                    /*def pid = sh(returnStdout: true, script: 'pgrep -f "/var/lib/jenkins/workspace/one-hundred-doors-pipeline/target/.*\\.jar"').trim()
                    echo 'pid value ${pid}'
                    if (pid) {
                        def processGID = sh(returnStdout: true, script: 'ps -o group= -p ${pid}').trim()
                        echo 'Process pid ${pid}'
                        echo 'Process group ${processGID}'
                        sh 'sudo usermod -aG ${processGID} jenkins'
                        sh 'pkill ${pid}'
                    }*/
                }
                sh 'nohup java -jar /var/lib/jenkins/workspace/one-hundred-doors-pipeline/target/*.jar > /dev/null 2>&1 &'
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