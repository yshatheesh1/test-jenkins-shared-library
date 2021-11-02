import com.bbc.core.CheckoutHandler
import com.bbc.model.Checkout
import com.bbc.step.DefaultStepExecutor
import com.bbc.step.IStepExecutor

def call(Map config) {
    IStepExecutor stepExecutor = new DefaultStepExecutor(this);
    Checkout checkout = new Checkout(
            versionClass: config.type,
            url: config.url,
            credentialId: config.credentialId,
            branchName: config.branchName,
            timeout: config.timeOut
    )
    new CheckoutHandler(stepExecutor, checkout).gitCheckout();
}