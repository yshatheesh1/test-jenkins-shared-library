def call(config =[:]) {
    echo "checkout project from branch ${scm.branches}"
    try {
        checkout([
            $class: 'GitSCM',
            branches: scm.branches,
            extensions: scm.extensions + [
                [$class: 'CloneOption',
                    timeout: config?.gitCloneTimeoutInMinutes ?: config.GIT_CLONE_TIMEOUT_IN_MINUTES],
                [$class: 'RelativeTargetDirectory', relativeTargetDir: ''],
                [$class: 'LocalBranch']
            ],
            userRemoteConfigs: [
                [
                    credentailsId: config?.githubCrendentialId ?: config.GITHUB_CRENDENTIAL_ID,
                    url: scm.userRemoteConfigs[0].url
                ]
            ]
        ])
    } catch (Exception ex) {
        echo "Checkout exception : ${ex}"
    }
}
