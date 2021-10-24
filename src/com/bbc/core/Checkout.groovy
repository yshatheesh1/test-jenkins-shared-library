package com.bbc.core

import com.bbc.step.IStepExecutor

class Checkout {

    private IStepExecutor _stepExecutor;

    Checkout(IStepExecutor stepExecutor) {
        this._stepExecutor = stepExecutor;
    }

    void gitCheckout(String $class, String repository, String credentialId, List scmBranches) {
        //checkout([$class: 'GitSCM', branches: [[name: '*/master'], [name: '*/develop']], extensions: [[$class: 'CheckoutOption', timeout: 3], [$class: 'CleanBeforeCheckout', deleteUntrackedNestedRepositories: true]], userRemoteConfigs: [[credentialsId: 'jenkins', url: 'https://github.com/yshatheesh1/test-dotnet-jenkins']]])
        try {
            this._stepExecutor.checkout([
                    $class           : $class,
                    branches         : scmBranches,
                    userRemoteConfigs: [
                            [
                                    credentialsId: credentialId,
                                    url          : repository
                            ]
                    ]
            ])
            this._stepExecutor.echo("Check-out complete...")
        } catch(Exception e) {
            this._stepExecutor.echo("Something went wrong during checkout - " + e.getMessage())
        }
    }
}
