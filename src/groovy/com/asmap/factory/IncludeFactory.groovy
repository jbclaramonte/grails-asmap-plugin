package com.asmap.factory

import com.asmap.model.Include
import org.apache.commons.logging.LogFactory

class IncludeFactory extends AbstractFactory {

    private static final log = LogFactory.getLog(this)

    public boolean isLeaf() {
        return true;
    }

    public Object newInstance(FactoryBuilderSupport builder, Object name, Object value, Map attributes) throws InstantiationException, IllegalAccessException {
        log.debug "IncludeFactory.newInstance : name=$name | value=$value | attributes=$attributes"

        return new Include(fields: [] + value)
    }

    public void setParent( FactoryBuilderSupport builder, Object parent, Object child ) {
        log.debug "IncludeFactory.setParent(parent:$parent, child:$child)"
        child.entity = parent.entity
    }

    public void setChild( FactoryBuilderSupport builder, Object parent, Object child ) {
        log.debug "IncludeFactory.setChild(parent:$parent, child:$child)"
    }

    public void onNodeCompleted( FactoryBuilderSupport builder, Object parent, Object node ) {
        log.debug "IncludeFactory.onNodeCompleted(parent:$parent, attributsToIncude:$node)"
    }

}