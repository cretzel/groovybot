package com.groovybot.engine.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.groovybot.engine.GroovyShellEngine;

@Singleton
public class GroovyShellEngineExecutionWrapperImpl extends
        AbstractGroovyEngineExecutionWrapperImpl {

    @Inject
    public GroovyShellEngineExecutionWrapperImpl(final GroovyShellEngine engine) {
        super(engine);
    }

}
