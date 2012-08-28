package com.asmap.model

import org.codehaus.groovy.grails.commons.GrailsClass
import org.apache.commons.logging.LogFactory

class Load extends Composite {

    private static final log = LogFactory.getLog(this)

    String fieldNameToLoad
    GrailsClass entityGrailsClass

    def asMap(Object anEntity) {
        log.debug "${this}.asMap on entity $anEntity"
        Map result

        if ( !anEntity ) {
            anEntity = entity
        }

        if ( isOneToMany() || isManyToMany() ) {
            def toManyArray = []
            anEntity.each { anEntityItem ->
                result = children.find { it instanceof Include }.asMap( anEntityItem )
                children.findAll { it instanceof Load }.each { Load load ->
                    result << load.asMap( anEntityItem."${load.fieldNameToLoad}" )
                }
                toManyArray << result
            }
            result = [ (fieldNameToLoad): toManyArray ]
        } else if ( isEmbedded() || isManyToOne() || isOneToOne() ) {
            result = children.find { it instanceof Include }.asMap( anEntity )
            children.findAll { it instanceof Load }.each { Load load ->
                result << load.asMap( anEntity."${load.fieldNameToLoad}" )
            }
            result = [ (fieldNameToLoad): result ]
        } else {
            result = children.find { it instanceof Include }.asMap( anEntity )
            children.findAll { it instanceof Load }.each { Load load ->
                Map r = load.asMap( anEntity."${load.fieldNameToLoad}" )
                result << r
            }
        }

        return result
    }

    String toString() {
        return "Load '$fieldNameToLoad'"
    }

}
