import com.bbc.core.CheckoutHandler
import com.bbc.steps.DefaultStepExecutor
import com.bbc.steps.IStepExecutor

/*
 * codePipeline {
 *      restore: ''
 *      buildType: 'dotNetCore'
 *      buildVersion: '3.1.0'
 *      build: 'dotnet build --configuration release'
 *      test: 'dotnet test'
 *      integrationTest: 'dotnet test --integrationTest'
 *      sonarScan: 'dotnet sonarScan'
 *      nuget: 'dotnet publish'
 * }
 */

def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    IStepExecutor stepExecutor = new DefaultStepExecutor(this);
    stepExecutor.echo(config.buildType as String)
    stepExecutor.echo(config.version as String)
    stepExecutor.echo(config.credentialId as String)
    node {
        stage('checkout') {
            CheckoutHandler checkoutHandler = new CheckoutHandler(stepExecutor);
            checkoutHandler.gitCheckout(scm.userRemoteConfigs[0].url, config.credentialId, scm.branches);
        }
    }
}
