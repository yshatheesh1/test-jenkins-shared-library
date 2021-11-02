import com.bbc.core.CheckoutHandler
import com.bbc.model.Checkout
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
    IStepExecutor stepExecutor = new DefaultStepExecutor(this);
    stepExecutor.echo(scm.userRemoteConfigs[0].url)
    stepExecutor.echo(scm.userRemoteConfigs[0].credentialsId)
    node('master') {
        stage('checkout') {
//            def d = [
//                    type: 'GitSCM',
//                    url: scm.userRemoteConfigs[0].url,
//                    credentialId: scm.userRemoteConfigs[0].credentialsId,
//                    branchName: scm.branches[0].name
//            ]
//            gitCheckout(d)
        }
        docker.image(config.image).inside {
            stage('build') {
                stepExecutor.sh(config.build)
            }
            stage('test') {
                stepExecutor.sh(config.test)
            }
        }
//
//        stage('sonar scan') {
////            dotnet tool install dotnet-sonarscanner --tools-path ~/tools
////            ~/tools/dotnet-sonarscanner begin /k:"project-key" /n:"project-name" /d:sonar.host.url="url"
////            /d:sonar.login="token" /d:sonar.cs.opencover.reportspath="**/coverage.opencover.xml"
////            /d:sonar.cs.vstest.reportsPath="**/TestResults/*.trx" /d:sonar.exclusions="exclusions"
////            /d:sonar.pullrequest.branch="branch" /d:sonar.pullrequest.key="pr_number"
////            dotnet build --configuration release
////            dotnet test
////            ~/tools/dotnet-sonarscanner end /d.sonar.login="sonartoken"
//
//            // use jacoco maven or jacoco gradle plugin
//
//
//        }
    }
}
