package com.bbc.core

import com.bbc.model.PipelineConfig
import com.bbc.step.IStepExecutor

/**
 * Helper class for branches
 */
class BranchHandler extends BasePipeline {

    BranchHandler(IStepExecutor stepExecutor, PipelineConfig pipelineConfig) {
        super(stepExecutor, pipelineConfig)
    }

    boolean isMaster() {
        return pipelineConfig?.checkout?.branchName?.contains('master')
    }

    boolean isDevelopment() {
        return pipelineConfig?.checkout?.branchName?.contains('develop')
    }

    boolean isFeature() {
        return pipelineConfig?.checkout?.branchName?.matches("^feature.*\$")
    }

    boolean isBugFix() {
        return pipelineConfig?.checkout?.branchName?.matches("^bugfix.*\$")
    }
}
