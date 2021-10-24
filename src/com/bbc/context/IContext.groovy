package com.bbc.context

import com.bbc.steps.IStepExecutor

interface IContext {
    IStepExecutor getStepExecutor()
}
