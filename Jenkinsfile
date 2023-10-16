pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        git branch: 'master', url: 'https://github.com/gariktepanosian/newrepo.git'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn compile'
        sh 'mvn test'
      }
    }
    stage('Deploy') {
     when {
                     expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
                 }
                 steps {
                     sh 'deploy-script.sh'
                 }
    }
  }
}