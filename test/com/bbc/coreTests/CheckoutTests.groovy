package com.bbc.coreTests

import com.bbc.core.Checkout
import com.bbc.step.IStepExecutor
import groovy.mock.interceptor.MockFor
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CheckoutTests {

    @Test
    void testCheckout() {
        // arrange
        String $class = "git";
        String url = "url"
        String credentialId = "credentialId"
        List branches = Collections.singletonList({ name: "test_branch" })
        MockFor stepExecutorMockFor = new MockFor(IStepExecutor)
        stepExecutorMockFor.demand.with {
            checkout { Map parameters ->
                Assert.assertEquals(parameters.userRemoteConfigs[0].url, url)
                Assert.assertEquals(parameters.userRemoteConfigs[0].credentialsId, credentialId)
                Assert.assertEquals(parameters.branches, branches)
            }
            echo { String message -> Assert.assertEquals(message, "Check-out complete...") }
        }
        IStepExecutor stepExecutor = stepExecutorMockFor.proxyDelegateInstance()
        // act
        new Checkout(stepExecutor).gitCheckout($class, url, credentialId, branches)
        // assert
        stepExecutorMockFor.verify(stepExecutor)
    }

    @Test
    void testCheckout_HandleException() {
        // arrange
        MockFor stepExecutorMockFor = new MockFor(IStepExecutor)
        stepExecutorMockFor.demand.with {
            checkout { Map parameters -> throw new Exception('git exception') }
            echo { String message -> Assert.assertTrue(message.contains('git exception')) }
        }
        IStepExecutor stepExecutor = stepExecutorMockFor.proxyDelegateInstance()
        // act
        new Checkout(stepExecutor).gitCheckout("test", "url", "credential", null)
        // assert
        stepExecutorMockFor.verify(stepExecutor)
    }
}
