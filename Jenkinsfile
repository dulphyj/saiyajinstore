pipeline {
    agent any

    environment {
        COMPOSE_PROJECT_NAME = "saiyajinstore"
    }

    stages {
        stage('Clonar código') {
            steps {
                git 'git@github.com:dulphyj/saiyajinstore.git'
            }
        }

        stage('Reconstruir y levantar contenedores') {
            steps {
                script {
                    sh 'docker-compose down'
                    sh 'docker-compose build backend'
                    sh 'docker-compose up -d backend'
                }
            }
        }
    }
}
