import com.bbc.core.CheckoutHandler
import com.bbc.step.DefaultStepExecutor
import com.bbc.step.IStepExecutor

/*
 * codePipeline {
 *      buildType: 'dotNetCore'
 *      buildVersion: '3.1.0'
 *      image: 'dotnet:3.1.0'
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
    // get builder based on build
    //  dockerNode(config.image) {
    stage('checkout') {
        gitCheckout
    }
    stage('build and test') {
        step {
            IStepExecutor stepExecutor = new DefaultStepExecutor(this);
            stepExecutor.sh(config.build)
        }
        step {
            IStepExecutor stepExecutor = new DefaultStepExecutor(this);
            stepExecutor.sh(config.test)
        }
    }
}
