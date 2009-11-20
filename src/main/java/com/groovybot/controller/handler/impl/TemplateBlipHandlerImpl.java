package com.groovybot.controller.handler.impl;

import com.google.inject.Inject;
import com.groovybot.controller.handler.TemplateBlipHandler;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.guice.TemplateWrapper;
import com.groovybot.model.ScriptExecutionType;

public class TemplateBlipHandlerImpl extends AbstractPrefixedEngineBlipHandler
        implements TemplateBlipHandler {

    private final GroovyEngineExecutionWrapper engineExecutionWrapper;

    @Inject
    public TemplateBlipHandlerImpl(
            final @TemplateWrapper GroovyEngineExecutionWrapper engineExecutionWrapper) {
        super(TemplateBlipHandler.TEMPLATE_PREFIX);
        this.engineExecutionWrapper = engineExecutionWrapper;
    }

    @Override
    protected ScriptExecutionType getScriptExecutionType() {
        return ScriptExecutionType.TEMPLATE_BLIP;
    }

    @Override
    protected EngineResult executeScript(final String script) {
        return engineExecutionWrapper.execute(script);
    }

}
