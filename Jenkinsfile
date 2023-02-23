pipeline {
    agent any
    tools {
        jdk 'jdk 17'
        maven 'maven 3.9.0'
    }
    stages {
        stage("Checkout") {
               steps {
                    git url: 'https://github.com/davidJavac/one-hundred-doors.git', branch: 'master'
               }
          }

        stage("Compile") {
            steps {
                sh 'mvn -v'
                sh 'mvn compile'
            }
        }

        stage("Test") {
            steps {
                sh "mvn test"
            }
        }
    }
}