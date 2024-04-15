pipeline {
    agent any

    tools {
        maven 'my-maven'
    }

    environment {
        MYSQL_ROOT_LOGIN = credentials('mysql-root-login')
    }

    stages {
        stage('Build with Maven') {
            steps {
                echo 'Building..'
                sh 'mvn --version'
                sh 'java -version'
                sh 'mvn clean package -Dmaven.test.failure.ignore=true'
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
                echo 'Deploying and cleaning'
                // sh 'docker image pull mysql:8.0'
                // sh 'docker network create dev || echo "this network exists"'
                // sh 'docker container stop khalid-mysql || echo "this container does not exist" '
                // sh 'echo y | docker container prune '
                // sh 'docker volume rm khalid-mysql-data || echo "no volume"'

                sh "docker run --name mysql --rm --network dev -v khalid-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=db_example  -d mysql:8.0 "
                sh 'sleep 20'
                sh "docker exec -i mysql mysql --user=root --password=123456 < script"
            }
        }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning'
                sh 'docker image pull ledung94/springboot'
                sh 'docker container stop ledung94/springboot || echo "this container does not exist" '
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune '

                sh 'docker container run -d --rm --name ledung94/springboot -p 8081:8080 --network dev ledung94/springboot'
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