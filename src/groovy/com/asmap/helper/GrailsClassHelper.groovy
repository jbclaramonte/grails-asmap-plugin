package com.asmap.helper

import org.codehaus.groovy.grails.commons.GrailsClass

class GrailsClassHelper {
          
    def application
    
    GrailsClass findGrailsClassForDomainClass ( Class classToFind ) {
        application.domainClasses.find { grailsClass ->
            grailsClass.clazz == classToFind
        }
    }
    
    GrailsClass getReferencedDomainClass(Class domainClass, String property) {
        return findGrailsClassForDomainClass( domainClass ).getPropertyByName( property ).referencedDomainClass
    }
    
    GrailsClass getReferencedDomainClass(GrailsClass grailsClass, String property) {
        return grailsClass.getPropertyByName( property ).referencedDomainClass
    }    
    
    List<String> getSimpleFields (GrailsClass grailsClass) {
        def simplesFields = grailsClass.persistentProperties.findAll { property ->
            !(property.isEmbedded() || property.isManyToOne() || property.isOneToOne() || property.isOneToMany() || property.isManyToMany())
        }.collect {it.name} + ["id"]
        return simplesFields
    }    
    
}
