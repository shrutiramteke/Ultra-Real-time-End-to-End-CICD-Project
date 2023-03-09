/* groovylint-disable NestedBlockDepth */
pipeline{
    agent any 
    environment {
      DOCKER_TAG = getVersion()
    }
    stages{
        stage('sonar quality check'){
            agent{
                docker {
                    image 'maven'
                }
            }
            steps{
                script{
                    withSonarQubeEnv(credentialsId: 'sonar-token') {
                        sh 'mvn clean package sonar:sonar'
                    }
                }
            }
        }

        stage('Quality Gate status'){
            steps{
                script{
                    /* groovylint-disable-next-line DuplicateStringLiteral */
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-token'
                }
            }
        }
        
        stage('Docker Build'){
            steps{
                sh "docker build . -t ShrutiRamteke/webapp:${DOCKER_TAG} "
            }
        }
        
        stage('DockerHub Push'){
            steps{
                withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                    sh "docker login -u ShrutiRamteke -p ${dockerhubpwd}"
                }
                
                sh "docker push ShrutiRamteke/webapp:${DOCKER_TAG} "
            }
        }

        // stage('docker build & docker push to Nexus repo'){
        //     steps{
        //         script{

        //         }
        //     }
        // }
    }
}
def getVersion(){
    def commitHash = sh label: '', returnStdout: true, script: 'git rev-parse --short HEAD'
    return commitHash
}
