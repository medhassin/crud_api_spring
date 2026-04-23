pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Build en cours...'
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Tests en cours...'
                sh './mvnw test'
            }
        }

        stage('Check Docker') {
            steps {
                echo 'Vérification Docker...'
                sh 'docker -v'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Build Docker image...'
                sh 'docker build -t spring-app .'
            }
        }
    }

    post {
        success {
            echo 'Pipeline terminé avec succès 🎉'
        }
        failure {
            echo 'Pipeline échoué ❌'
        }
    }
}