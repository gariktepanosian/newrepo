pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git branch: 'master', url: 'https://github.com/gariktepanosian/newrepo.git'
      }
    }
    stage('Scan') {
      steps {
      withSonarQubeEnv(installationName: 'sonarqube')
        sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
      }
    }
  }
}