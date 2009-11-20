package com.groovybot.engine.impl;

import com.google.inject.Inject;
import com.groovybot.engine.GroovyShellEngine;
import com.groovybot.engine.GroovyShellEngineExecutionWrapper;

public class GroovyShellEngineExecutionWrapperImpl extends
        AbstractGroovyEngineExecutionWrapperImpl implements
        GroovyShellEngineExecutionWrapper {

    @Inject
    public GroovyShellEngineExecutionWrapperImpl(final GroovyShellEngine engine) {
        super(engine);
    }

}
