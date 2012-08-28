package com.asmap.factory

import com.asmap.model.Load
import org.apache.commons.logging.LogFactory

class LoadFactory extends AbstractFactory {

    private static final log = LogFactory.getLog(this)

    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        log.debug "LoadFactory.newInstance : name=$name | value=$value | attributes=$attributes"
        if ( value instanceof String ) {
            return new Load(fieldNameToLoad: value)
        } else if ( value instanceof Load ) {
            return value
        }
        return null
    }

    public void setParent( FactoryBuilderSupport builder, Object parent, Object child ) {
        log.debug "LoadFactory.setParent(parent:$parent, child:$child)"
        child.entityGrailsClass = builder.grailsClassHelper.getReferencedDomainClass( parent.entityGrailsClass, child.fieldNameToLoad)
        if (parent.entity) {
            child.entity = parent.entity."${child.fieldNameToLoad}"
        }
    }

    public void setChild( FactoryBuilderSupport builder, Object parent, Object child ) {
        log.debug "LoadFactory.setChild(parent:$parent, child:$child)"
        parent.addChild child
    }

    public void onNodeCompleted( FactoryBuilderSupport builder, Object parent, Object node ) {
        log.debug "LoadFactory.onNodeCompleted(parent:$parent, attributsToIncude:$node)"
    }
}
