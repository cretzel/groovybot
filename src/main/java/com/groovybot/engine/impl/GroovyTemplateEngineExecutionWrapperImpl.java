package com.groovybot.engine.impl;

import com.google.inject.Inject;
import com.groovybot.engine.GroovyTemplateEngine;
import com.groovybot.engine.GroovyTemplateEngineExecutionWrapper;

public class GroovyTemplateEngineExecutionWrapperImpl extends
        AbstractGroovyEngineExecutionWrapperImpl implements
        GroovyTemplateEngineExecutionWrapper {

    @Inject
    public GroovyTemplateEngineExecutionWrapperImpl(
            final GroovyTemplateEngine engine) {
        super(engine);
    }

}
