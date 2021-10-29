package com.bbc.coreTests

import com.bbc.core.BranchHandler
import com.bbc.model.Checkout
import com.bbc.model.PipelineConfig
import com.bbc.step.DefaultStepExecutor
import org.junit.Assert
import org.junit.Test

class BranchTests {

    @Test
    void testMaster() {
        BranchHandler branch = new BranchHandler(new DefaultStepExecutor(this),
                new PipelineConfig(checkout: new Checkout(branchName: 'master')))
        Assert.assertTrue(branch.isMaster());
    }

    @Test
    void testDevelop() {
        BranchHandler branch = new BranchHandler(new DefaultStepExecutor(this),
                new PipelineConfig(checkout: new Checkout(branchName: 'develop')))
        Assert.assertTrue(branch.isDevelopment());
    }

    @Test
    void testFeature() {
        BranchHandler branch = new BranchHandler(new DefaultStepExecutor(this),
                new PipelineConfig(checkout: new Checkout(branchName: 'feature-123')))
        Assert.assertTrue(branch.isFeature());
    }

    @Test
    void testBugFix() {
        BranchHandler branch = new BranchHandler(new DefaultStepExecutor(this),
                new PipelineConfig(checkout: new Checkout(branchName: 'bugfix_testing-123')))
        Assert.assertTrue(branch.isBugFix());
    }
}
