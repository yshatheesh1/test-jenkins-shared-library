def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    node {
        stage('checkout') {
            echo $env
        }
        stage('test') {
            switch (config.buildType.toLowerCase()) {
                case 'flutter':
                    echo 'flutter'
                    break
                case 'dotnetcore' :
                    echo 'dotnetcore'
                    break
                case 'java':
                    echo 'java'
                    break
                case 'angular':
                    echo 'angular'
                    break
                case 'infrastructure':
                    echo 'infrastructure'
                    break
            }
        }
    }

}
