package com.bbc.modelTests

import com.bbc.model.Job
import com.bbc.model.PipelineConfig

class WorkflowTests {

    void createModel() {
        def workflow = new PipelineConfig(
                jobs: [
                        new Job(name: 'setup',)
                ]
        )
    }
}
