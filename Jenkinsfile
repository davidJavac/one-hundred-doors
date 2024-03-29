pipeline {
    agent any

    environment {
        IMAGE_NAME = 'davidfravor/one_hundred_doors'
        CONTAINER_NAME = 'one-hundred-doors-container'
    }

    tools {
        jdk 'jdk 17'
        maven 'maven 3.9.0'
    }

    stages {
        stage("Checkout") {
            steps {
                script {
                    def branch = ''
                    if (fileExists('.git')) {
                        if (env.ACTION == null && env.MERGED == null) {
                            branch = sh(returnStdout: true, script: "git branch --contains HEAD | grep -v 'HEAD detached' | awk '{print \$NF}'").trim()
                        } else if (env.ACTION == "closed" && env.MERGED == "true") {
                            branch = env.BASE_BRANCH
                        } else {
                            branch = env.CHANGE_BRANCH
                        }
                        echo "base branch ${env.BASE_BRANCH}, change branch ${env.CHANGE_BRANCH}"
                        echo "action ${env.ACTION}, merged ${env.MERGED}"
                    }

                    echo "branch variable ${branch}"

                    // Checkout the specific branch
                    checkout([$class: 'GitSCM', branches: [[name: branch]], userRemoteConfigs: [[url: 'https://github.com/davidJavac/one-hundred-doors.git']]])
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
                    publishHTML(target: [
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

        stage("Docker build") {
            steps {
                sh "whoami"
                sh "docker build -t $IMAGE_NAME ."
            }
        }

        stage("Docker push") {
            steps {
                sh "docker login"
                sh "docker push $IMAGE_NAME"
            }
        }

        stage("Deploy to staging") {
            steps {
                script {
                    def portInUse = sh(script: "sudo lsof -i :8081", returnStatus: true, returnStdout: true)
                    if (portInUse == 0) {
                        echo "Port 8081 is already in use. Unable to proceed."
                        sh "sudo kill -9 \$(sudo lsof -t -i :8081)"
                    }
                }

                sh "docker run -d --rm -p 8081:8081 --name $CONTAINER_NAME $IMAGE_NAME"
            }
        }

        stage("Acceptance test") {
            steps {
                sleep 10
                sh "mvn verify -Pacceptance-test"
            }
        }

        stage("Deploy") {
            steps {
                script {
                    sh 'sudo pkill -f "one-hundred-doors-practice-1.0-SNAPSHOT.jar" || true'
                    sh "sudo nohup java -jar /var/lib/jenkins/workspace/one-hundred-doors-pipeline/target/one-hundred-doors-practice-1.0-SNAPSHOT.jar > output.log 2>&1 &"
                }
            }
        }
    }

    post {
        always {
            sh "docker stop $CONTAINER_NAME"
            /*mail to: 'david.abramovich84@gmail.com',
            subject: "Completed Pipeline: ${currentBuild.fullDisplayName}",
            body: "Your build completed, please check: ${env.BUILD_URL}"
            echo "build completed ${env.BUILD_URL}, pipeline ${currentBuild.fullDisplayName}"*/
        }
    }
}
