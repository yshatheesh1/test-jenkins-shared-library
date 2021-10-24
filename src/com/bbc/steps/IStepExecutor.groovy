package com.bbc.steps

interface IStepExecutor {
    int sh(String command)

    void error(String message)

    void checkout(Map parameters)

    void echo(String message)
}