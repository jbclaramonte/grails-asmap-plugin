package com.asmap

import com.asmap.factory.IncludeFactory
import com.asmap.factory.LoadFactory

class SimpleBuilder extends FactoryBuilderSupport {

    def grailsClassHelper

    public SimpleBuilder(boolean init = true) {
        super(init)
    }

    def registerAll() {
        registerFactory( "load", new LoadFactory())
        registerFactory( "include", new IncludeFactory())
    }

}
