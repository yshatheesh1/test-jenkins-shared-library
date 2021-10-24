import com.bbc.core.Checkout
import com.bbc.step.DefaultStepExecutor
import com.bbc.step.IStepExecutor
import groovy.json.JsonBuilder

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
            stepExecutor.echo(scm.userRemoteConfigs[0].credentialsId as String)
            stepExecutor.echo(scm.branches as String)
            stepExecutor.echo(scm.userRemoteConfigs[0].url as String)
            Checkout checkoutHandler = new Checkout(stepExecutor);
            checkoutHandler.gitCheckout("GitSCM", scm.userRemoteConfigs[0].url, scm.userRemoteConfigs[0].credentialsId, scm.branches);
        }
    }
}
