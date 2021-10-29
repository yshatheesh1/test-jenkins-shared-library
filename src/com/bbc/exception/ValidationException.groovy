package com.bbc.exception

import groovy.transform.CompileStatic

@CompileStatic
class ValidationException extends Exception {

    ValidationException(String message) {
        super(message)
    }
}
