pipeline { 
    agent any 
    stages { 
        stage('Checkout') { 
            steps { 
                echo "Checking out backend code..." 
                checkout scm 
            } 
        } 
        stage('Build') { 
            steps { 
                echo "Building backend..." 
                bat 'npm install' 
                bat 'npm run build' 
            } 
        } 
    } 
    post { 
        success { echo "Build successful!" } 
        failure { echo "Build failed!" } 
    } 
} 
