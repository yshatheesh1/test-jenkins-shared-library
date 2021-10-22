package com.bbc.jenkins

import groovy.transform.CompileStatic

@CompileStatic
class Branch implements Serializable {

    private static final long serialVersionUID = 1L

    String name

    Branch(String name) {
     this.name = Objects.requireNonNull(name)
    }

    boolean isMaster() {
       return name == 'master'
    }

    boolean isDevelopment() {
        return name == 'development'
    }

    boolean isFeature() {
        return name == 'feature'
    }

}
