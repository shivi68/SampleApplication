pipeline {
    agent any

    environment {
        REPO_URL = 'https://github.com/shivi68/SampleApplication.git'
        BRANCH = 'master'
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    echo 'Checking out source code...'
                }
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: "*/${BRANCH}"]],
                    userRemoteConfigs: [[
                        url: "${REPO_URL}"
                    ]]
                ])
            }
        }

        stage('Code Clean') {
            steps {
                script {
                    echo 'Cleaning code...'
                }
                bat 'mvn clean'
            }
        }

        stage('Code Compile') {
            steps {
                script {
                    echo 'Compiling code...'
                }
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                script {
                    echo 'Running tests...'
                }
                bat 'mvn test'
            }
        }

        stage('Deployment') {
            steps {
                script {
                    echo 'Simulating deployment...'
                    def deploymentStatus = true // Mock condition for deployment success
                    if (deploymentStatus) {
                        echo 'Deployment successful!'
                    } else {
                        error 'Deployment failed!'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Check logs for details.'
        }
        always {
            echo 'Cleaning up workspace...'
            cleanWs()
        }
    }
}
