# client-manager
 
 
 stage('List All Tools') {
            steps {
                script {
                    def allTools = toolDescriptors.findAll()
                    
                    echo "Available tools:"
                    allTools.each { toolDescriptor ->
                        def toolInstallation = toolDescriptor.installs.find { install ->
                            install.name == toolDescriptor.getName()
                        }
                        if (toolInstallation != null) {
                            echo "  - ${toolDescriptor.getName()} (${toolInstallation.home})"
                        }
                    }
                }
            }
        }
