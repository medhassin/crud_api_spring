pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "spring-app:latest"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
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

        stage('Check Docker (SAFE)') {
            steps {
                echo 'Vérification Docker...'
                sh '''
                    if command -v docker >/dev/null 2>&1
                    then
                        docker -v
                        echo "Docker OK"
                    else
                        echo "❌ Docker NON disponible dans Jenkins"
                        echo "👉 Skipping Docker stages"
                        exit 0
                    fi
                '''
            }
        }

        stage('Docker Build') {
            when {
                expression {
                    return sh(script: "command -v docker", returnStatus: true) == 0
                }
            }
            steps {
                echo 'Build Docker image...'
                sh 'docker build -t $DOCKER_IMAGE .'
            }
        }

        stage('Docker Run (test)') {
            when {
                expression {
                    return sh(script: "command -v docker", returnStatus: true) == 0
                }
            }
            steps {
                echo 'Lancement container test...'
                sh '''
                    docker stop spring-app || true
                    docker rm spring-app || true
                    docker run -d --name spring-app -p 8081:8081 $DOCKER_IMAGE
                '''
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