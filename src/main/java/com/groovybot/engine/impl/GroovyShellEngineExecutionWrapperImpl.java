package com.groovybot.engine.impl;

import com.google.inject.Inject;
import com.groovybot.engine.GroovyShellEngine;

public class GroovyShellEngineExecutionWrapperImpl extends
        AbstractGroovyEngineExecutionWrapperImpl {

    @Inject
    public GroovyShellEngineExecutionWrapperImpl(final GroovyShellEngine engine) {
        super(engine);
    }

}
