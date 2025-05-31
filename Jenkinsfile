pipeline {
    agent any

    environment {
        COMPOSE_PROJECT_NAME = "saiyajinstore"
    }

    stages {
        stage('Clonar código') {
            steps {
                checkout([$class: 'GitSCM',
          branches: [[name: '*/main']],
          userRemoteConfigs: [[
            url: 'git@github.com:dulphyj/saiyajinstore.git',
            credentialsId: 'github-deply-key-saijain'
          ]]
])

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
