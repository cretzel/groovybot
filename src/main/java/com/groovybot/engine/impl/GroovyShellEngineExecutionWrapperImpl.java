package com.groovybot.engine.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.groovybot.engine.GroovyShellEngine;
import com.groovybot.engine.result.EngineResultFactory;

@Singleton
public class GroovyShellEngineExecutionWrapperImpl extends
        AbstractGroovyEngineExecutionWrapperImpl {

    @Inject
    public GroovyShellEngineExecutionWrapperImpl(
            final GroovyShellEngine engine, final EngineResultFactory resultFactory) {
        super(engine, resultFactory);
    }

}
