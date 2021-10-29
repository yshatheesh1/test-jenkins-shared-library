package com.bbc.coreTests

import com.bbc.core.CheckoutHandler
import com.bbc.exception.ValidationException
import com.bbc.model.Checkout
import com.bbc.step.IStepExecutor
import static org.mockito.Mockito.*;
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CheckoutTests {

    private IStepExecutor stepExecutor;

    @Before
    void before() {
        stepExecutor = mock(IStepExecutor);
    }

    @Test
    void testCheckout_ThrowValidationCheckoutConfig() {
        // act
        def exception = Assert.assertThrows(ValidationException, () ->
                new CheckoutHandler(stepExecutor, null).gitCheckout())
        Assert.assertEquals('Checkout Config is Required.', exception.message)
    }

    @Test
    void testCheckout_ThrowValidationVersionClass() {
        // act
        def exception = Assert.assertThrows(ValidationException, () ->
                new CheckoutHandler(stepExecutor, new Checkout()).gitCheckout())
        Assert.assertEquals('versionClass is Required.', exception.message)
    }

    @Test
    void testCheckout_ThrowValidationBranchName() {
        // act
        def exception = Assert.assertThrows(ValidationException, () ->
                new CheckoutHandler(stepExecutor,
                        new Checkout(
                                versionClass: 'test'
                        )
                ).gitCheckout())
        Assert.assertEquals('branchName is Required.', exception.message)
    }

    @Test
    void testCheckout_ThrowValidationUrl() {
        // act
        def exception = Assert.assertThrows(ValidationException, () ->
                new CheckoutHandler(stepExecutor,
                        new Checkout(
                                versionClass: 'test',
                                branchName: 'branchName'
                        )
                ).gitCheckout())
        Assert.assertEquals('url is Required.', exception.message)
    }

    @Test
    void testCheckout() {
        // arrange
        Checkout checkout = new Checkout(versionClass: 'test',
                branchName: 'testBranch', url: 'testUrl');
        doNothing().when(stepExecutor).echo(anyString())
        doNothing().when(stepExecutor).checkout(anyMap())
        // act
        new CheckoutHandler(stepExecutor, checkout).gitCheckout()
        // assert
        verify(stepExecutor, times(1)).checkout(any());
    }

}