import com.bbc.core.CheckoutHandler
import com.bbc.model.Checkout
import com.bbc.step.DefaultStepExecutor
import com.bbc.step.IStepExecutor

def call(Map config) {
    IStepExecutor stepExecutor = new DefaultStepExecutor(this);
    stepExecutor.echo(config.type )
    stepExecutor.echo(config.url )
    stepExecutor.echo(config.credentialId )
    stepExecutor.echo(config.branchName )
    Checkout checkout = new Checkout(
            versionClass: config.type ?: 'GitSCM',
            url: config.url ?:  scm.userRemoteConfigs[0].url,
            credentialId: config.credentialId ?: scm.userRemoteConfigs[0].credentialsId,
            branchName: config.branchName ?: scm.branches[0].name
    )
    new CheckoutHandler(stepExecutor, checkout).gitCheckout();
}