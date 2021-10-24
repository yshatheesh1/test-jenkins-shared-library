import com.bbc.core.CheckoutHandler

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
    echo config.buildType
    echo config.version
    echo config.credentialId
    node {
        stage('checkout') {
            CheckoutHandler checkoutHandler = new CheckoutHandler(checkout);
            checkoutHandler.gitCheckout(scm.userRemoteConfigs[0].url, config.credentialId, scm.Branches);
        }
    }
}
