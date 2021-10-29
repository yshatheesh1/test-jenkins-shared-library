package com.bbc.core

import com.bbc.exception.ValidationException
import com.bbc.model.Checkout
import com.bbc.step.IStepExecutor

class CheckoutHandler {

    protected IStepExecutor stepExecutor;

    protected Checkout checkout;

    CheckoutHandler(IStepExecutor stepExecutor, Checkout checkout) {
        this.stepExecutor = stepExecutor;
        this.checkout = checkout;
    }

    void gitCheckout() {
        if (!checkout) {
            throw new ValidationException("Checkout Config is Required.")
        }
        if (!checkout.versionClass) {
            throw new ValidationException("versionClass is Required.")
        }
        if (!checkout.branchName) {
            throw new ValidationException("branchName is Required.")
        }
        if (!checkout.url) {
            throw new ValidationException("url is Required.")
        }
        def scmBranches = Collections.singletonList([name: checkout.branchName])
        def userRemoteConfigs = [[url: checkout.url]]
        if (checkout.credentialId?.trim()) {
            userRemoteConfigs.add([credentialsId: checkout.credentialId])
        }
        def extensions = [[$class: 'CleanBeforeCheckout', deleteUntrackedNestedRepositories: true]]
        if (checkout?.timeout) {
            extensions.add([$class: 'CheckoutOption', timeout: checkout?.timeout])
        }

        stepExecutor.echo("Started Check-out...")
        stepExecutor.checkout([
                $class           : checkout.versionClass,
                branches         : scmBranches,
                userRemoteConfigs: userRemoteConfigs,
                extensions       : extensions
        ])
        stepExecutor.echo("Check-out completed...")
    }
}
