package com.bbc.core

import com.bbc.steps.IStepExecutor

class CheckoutHandler {

    private IStepExecutor _stepExecutor;

    CheckoutHandler(IStepExecutor stepExecutor) {
        this._stepExecutor = stepExecutor;
    }

    void gitCheckout(String url, String credentialId, String[] branchNames) {
        def scmBranches = branchNames.collect { [name: it] }
        this._stepExecutor.checkout([
                $class           : 'GitSCM',
                branches         : scmBranches,
                userRemoteConfigs: [
                        [
                                credentailsId: credentialId,
                                url          : url
                        ]
                ]
        ])
    }
}
