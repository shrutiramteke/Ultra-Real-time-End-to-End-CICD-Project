/* groovylint-disable NestedBlockDepth */
pipeline{
    agent any 
    environment {
      DOCKER_TAG = getVersion()
      VERSION = "${env.BUILD_ID}"
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
                sh "docker build . -t shrutiramteke/webapp:${DOCKER_TAG} "
            }
        }
        
        stage('DockerHub Push'){
            steps{
                withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                    sh "docker login -u shrutiramteke -p ${dockerhubpwd}"
                }
                
                sh "docker push shrutiramteke/webapp:${DOCKER_TAG} "
            }
        }
       stage('docker push to Nexus repo'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'nexus_passwd', variable: 'nexus_creds')]) {
                        sh '''
                         docker build -t 13.231.143.42:8083/springapp:${VERSION} .
                         docker login -u admin -p $nexus_creds 13.231.143.42:8083
                         docker push 13.231.143.42:8083/springapp:${VERSION}
                         docker rmi 13.231.143.42:8083/springapp:${VERSION}
                        '''
                    }
                }
            }
        }
	    
	  stage('Identifying misconfigs using datree in helm charts'){
                steps{
                     script{   
	                 dir('kubernetes/myapp/') {
                           sh 'helm plugin datree test .'
                               }
		          }
	           }
	  }
    }
    
    post {
		always {
			mail bcc: '', body: "<br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}", cc: '', charset: 'UTF-8', from: '', mimeType: 'text/html', replyTo: '', subject: "${currentBuild.result} CI: Project name -> ${env.JOB_NAME}", to: "shrutiramteke44@gmail.com";  
		}
	}
}
def getVersion(){
    def commitHash = sh label: '', returnStdout: true, script: 'git rev-parse --short HEAD'
    return commitHash
}
