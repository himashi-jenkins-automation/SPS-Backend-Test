pipeline { 
    agent any 
    stages { 
        stage('Checkout') { 
            steps { 
                echo "Checking out backend code..." 
                checkout scm 
            } 
        } 
        stage('Install') { 
            steps { 
                echo "Installing backend dependencies..." 
                bat 'npm install' 
            } 
        } 
    } 
    post { 
        success { echo "Backend dependencies installed successfully!" } 
        failure { echo "Backend installation failed!" } 
    } 
} 
