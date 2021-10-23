package com.bbc.core

import com.lesfurets.jenkins.unit.BasePipelineTest
import org.junit.Assert
import org.junit.Test

class BranchTests extends BasePipelineTest {

    @Test
    void testBranchNames() {
        Assert.assertTrue(new Branch("master").isMaster());
        Assert.assertTrue(new Branch("develop").isDevelopment());
        Assert.assertTrue(new Branch("feature-123").isFeature());
        Assert.assertTrue(new Branch("feature/574").isFeature());
        Assert.assertTrue(new Branch("bugfix_testing").isBugFix());
    }
}
