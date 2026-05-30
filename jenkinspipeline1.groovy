pipeline {

    agent {
        label 'ansible-node'
    }

    environment {

        DEV_REPO = 'https://github.com/insightedgepune/JenkinsProject.git'

        DEVOPS_REPO = 'https://github.com/insightedgepune/devopscode.git'
    }

    stages {

        stage('Checkout Dev Repo') {

            steps {

                git branch: 'main',
                credentialsId: 'git-token',
                url: "${DEV_REPO}"
            }
        }

        stage('Checkout DevOps Repo') {

            steps {

                dir('devops') {

                    git branch: 'main',
                    credentialsId: 'git-token',
                    url: "${DEVOPS_REPO}"
                }
            }
        }

        stage('Deploy Flask App') {

            steps {

                sshagent(['target-server-key']) {

                    sh '''
                    ansible-playbook \
                    -i devops/inventory \
                    devops/deploy_20may.yml
                    '''
                }
            }
        }
    }

    post {

        success {

            echo 'Deployment Successful'
        }

        failure {

            echo 'Deployment Failed'
        }
    }
}
