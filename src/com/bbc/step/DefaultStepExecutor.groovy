package com.bbc.step

import hudson.plugins.git.*

class DefaultStepExecutor implements IStepExecutor {

    private def _steps

    DefaultStepExecutor(steps) {
        this._steps = steps
    }

    @Override
    int sh(String command) {
        this._steps.sh(returnStatus: true, script: "${command}")
    }

    @Override
    void error(String message) {
        this._steps.error(message)
    }

    @Override
    void checkout(Map parameters) {
        this._steps.checkout(parameters)
    }

    @Override
    void echo(String message) {
        this._steps.echo(message);
    }
}
