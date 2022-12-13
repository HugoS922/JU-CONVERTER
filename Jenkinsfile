pipeline {
    agent any
    stages {
        stage ('Execution') {
             steps {
                 dir('./spring-boot-hello-world') {
                    sh 'chmod +x gradlew'
                    sh 'chmod +x gradle'
                 }
             }
        }
        stage('Assemble') {
            steps {
                dir('./spring-boot-hello-world') {
                    sh './gradlew assemble'
                }
            }
        }

        stage('Test') {
             steps {
                 dir('./spring-boot-hello-world') {
                     sh './gradlew test'
                  }
              }
         }
        stage('Sonarqube') {
            steps {
                dir('./spring-boot-hello-world') {
                    sh './gradlew sonarqube \
-Dsonar.host.url=http://localhost:9000 \
-Dsonar.login=c3190ced4a30d339f1a8f1f7c9755ac610a59542'
                }
            }
        }
    }
}
