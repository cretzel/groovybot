package com.groovybot.controller.handler.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.groovybot.controller.handler.TemplateBlipHandler;
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.guice.TemplateWrapper;
import com.groovybot.model.ScriptExecutionType;
import com.groovybot.persistence.ScriptExecutionEntityDao;

@Singleton
public class TemplateBlipHandlerImpl extends AbstractPrefixedEngineBlipHandler
        implements TemplateBlipHandler {

    private final GroovyEngineExecutionWrapper engineExecutionWrapper;

    @Inject
    public TemplateBlipHandlerImpl(
            final @TemplateWrapper GroovyEngineExecutionWrapper engineExecutionWrapper,
            final BlipHandlerResponseStrategy responseStrategy,
            final EngineResultFormatter engineResultFormatter,
            final ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao) {
        super(TemplateBlipHandler.TEMPLATE_PREFIX, responseStrategy,
                engineResultFormatter, groovyBotScriptExecutionEntityDao);
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
