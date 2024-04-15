pipeline {
    agent any

    tools {
        maven 'my-maven'
    }

    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-root-login')
    }

    stages {
        stage('Check Environment Variables') {
            steps {
                echo "MYSQL_ROOT_LOGIN: ${env.MYSQL_ROOT_LOGIN}"
                echo "MYSQL_ROOT_LOGIN_PSW: ${env.MYSQL_ROOT_LOGIN_PSW}"
            }
        }

        stage('Packaging/Pushing imagae') {
            steps {
                echo 'Packaging/Pushing..'
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t ledung94/springboot .'
                    sh 'docker push ledung94/springboot'
                }
            }
        }

        stage('Deploy MySQL to DEV') {
            steps {
                echo 'Deploying and cleaning msql'
                sh 'docker image pull mysql:8.0'
                sh 'docker network create dev || echo "this network exists"'
                sh 'docker container stop khalid-mysql || echo "this container does not exist" '
                sh 'echo y | docker container prune '
                sh 'docker volume rm khalid-mysql-data || echo "no volume"'

                sh "docker run --name khalid-mysql --rm --network dev -v khalid-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=${MYSQL_ROOT_LOGIN_PSW} -e MYSQL_DATABASE=db_example  -d mysql:8.0 "
                sh 'sleep 20'
                sh "docker exec -i khalid-mysql mysql --user=root --password=${MYSQL_ROOT_LOGIN_PSW} < script"
            }
        }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning springboot'
                sh 'docker image pull ledung94/springboot'
                sh 'docker container stop ledung94/springboot || echo "this container does not exist" '
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune '

                sh 'docker container run -d --rm --name ledung94/springboot -p 8081:8080 --network dev ledung94/springboot'
            }
        }

        stage('Build with Maven') {
            steps {
                echo 'Building..'
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
            }
        }
    }

    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}