package com.asmap.model

import org.apache.commons.logging.LogFactory

class Include extends Leaf {

    private static final log = LogFactory.getLog(this)

    def asMap(Object anEntity) {
        log.debug "${this}.asMap on entity $anEntity"
        def result = [:]
        fields.each { field ->
            result[(field)] = anEntity."$field"
        }
        return result
    }


    String toString() {
        return "Include '$fields'"
    }

}
