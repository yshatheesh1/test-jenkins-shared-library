package com.bbc.step

class DefaultStepExecutor implements IStepExecutor {

    private def steps

    DefaultStepExecutor(steps) {
        this.steps = steps
    }

    @Override
    int sh(String command) {
        this.steps.sh(returnStatus: true, script: "${command}")
    }

    @Override
    void error(String message) {
        this.steps.error(message)
    }

    @Override
    void checkout(Map parameters) {
        this.steps.checkout(parameters)
    }

    @Override
    void echo(String message) {
        this.steps.echo(message);
    }

    @Override
    void node(String label, Closure body) {
        this.steps.node(label, body)
    }

    @Override
    void stage(String label, Closure body) {
        this.steps.stage(label, body)
    }
}
