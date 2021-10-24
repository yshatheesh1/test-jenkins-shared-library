package com.bbc.context

import com.bbc.steps.DefaultStepExecutor
import com.bbc.steps.IStepExecutor

class DefaultContext implements IContext, Serializable {
    private _steps

    DefaultContext(steps) {
        this._steps = steps
    }

    @Override
    IStepExecutor getStepExecutor() {
        return new DefaultStepExecutor(this._steps)
    }
}