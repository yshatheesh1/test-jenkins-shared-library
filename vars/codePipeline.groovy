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
    IStepExecutor stepExecutor = new DefaultStepExecutor(this);
    // get builder based on build
    timeoutWithMsg(time: 15, unit: 'MINUTES', action: 'build') {
        //  dockerNode(config.image) {
        stage('checkout') {
            gitCheckout
        }
        stage('build') {
            stepExecutor.sh(config.build)
        }

        stage('test') {
            stepExecutor.sh(config.test)
        }

    }
}
