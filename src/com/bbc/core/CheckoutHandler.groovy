package com.bbc.core

class CheckoutHandler {
    def checkout;

    CheckoutHandler(checkout) {
        this.checkout = checkout;
    }

    void gitCheckout(String url, String credentialId, String[] branchNames) {
        def scmBranches = branchNames.collect { [name: it] }
        checkout([
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
