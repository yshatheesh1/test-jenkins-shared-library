package com.bbc.core

/**
 * Helper class for branches
 */
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
        return name == 'develop'
    }

    boolean isFeature() {
        return name.matches("^feature.*\$")
    }

    boolean isBugFix() {
        return name.matches("^bugfix.*\$")
    }
}
