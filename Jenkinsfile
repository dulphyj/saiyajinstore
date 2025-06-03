pipeline {
    agent any

    tools {
        maven 'Maven 3.9.9'
    }

    environment {
        COMPOSE_PROJECT_NAME = "saiyajinstore"
    }

    stages {
        stage('Clon code') {
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

        stage('Inject .env') {
            steps {
                withCredentials([file(credentialsId: 'saijain-env', variable: 'ENV_FILE')]) {
                    sh 'cp $ENV_FILE .env'
                }
            }
        }

	stage('Run tests') {
    		steps {
        		dir('backend') {
            			sh '''
                			set -o allexport
                			. ../.env
                			set +o allexport
                			mvn test
            			'''
        		}
    		}
	}

        stage('Rebuild and lift containers') {
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
