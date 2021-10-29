import com.bbc.core.CheckoutHandler
import com.bbc.model.Checkout
import com.bbc.step.DefaultStepExecutor
import com.bbc.step.IStepExecutor

def call() {
    IStepExecutor stepExecutor = new DefaultStepExecutor(this);
    Checkout checkout = new Checkout(
            versionClass: 'GitSCM',
            url: scm.userRemoteConfigs[0].url,
            credentialId:  scm.userRemoteConfigs[0].credentialsId,
            branchName: scm.branches[0].name
    )
    new CheckoutHandler(stepExecutor, checkout).gitCheckout();
}