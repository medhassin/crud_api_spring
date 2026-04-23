pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Build en cours...'
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                echo 'Tests...'
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                echo 'Build Docker...'
                sh 'docker build -t spring-app .'
            }
        }
    }
}