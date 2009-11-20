package com.groovybot.engine.impl;

import com.google.inject.Inject;
import com.groovybot.engine.GroovyTemplateEngine;

public class GroovyTemplateEngineExecutionWrapperImpl extends
        AbstractGroovyEngineExecutionWrapperImpl {

    @Inject
    public GroovyTemplateEngineExecutionWrapperImpl(
            final GroovyTemplateEngine engine) {
        super(engine);
    }

}
