package com.groovybot.engine.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.groovybot.engine.GroovyTemplateEngine;

@Singleton
public class GroovyTemplateEngineExecutionWrapperImpl extends
        AbstractGroovyEngineExecutionWrapperImpl {

    @Inject
    public GroovyTemplateEngineExecutionWrapperImpl(
            final GroovyTemplateEngine engine) {
        super(engine);
    }

}
