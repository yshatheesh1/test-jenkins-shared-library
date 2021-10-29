package com.bbc.step

interface IStepExecutor {
    int sh(String command)

    void error(String message)

    void checkout(Map parameters)

    void echo(String message)

    void node(String label, Closure body)

    void stage(String label, Closure body)
}