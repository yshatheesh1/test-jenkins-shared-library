package com.bbc.core

import com.bbc.model.PipelineConfig
import com.bbc.step.IStepExecutor

abstract class BasePipeline {

    protected IStepExecutor stepExecutor;

    protected PipelineConfig pipelineConfig;

    BasePipeline(IStepExecutor stepExecutor, PipelineConfig pipelineConfig) {
        this.stepExecutor = stepExecutor;
        this.pipelineConfig = pipelineConfig;
    }
}
