pipeline {
    agent any

    tools {
        maven 'Maven'  // Must match the name configured in Jenkins Global Tool Config
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/YOUR_USERNAME/temperature-converter.git'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('JaCoCo Report') {
            steps {
                sh 'mvn jacoco:report'
            }
            post {
                always {
                    jacoco(
                        execPattern: '**/target/jacoco.exec',
                        classPattern: '**/target/classes',
                        sourcePattern: '**/src/main/java'
                    )
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t temperature-converter:latest .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh 'docker run --rm temperature-converter:latest'
            }
        }
    }
}