package com.groovybot.controller.handler.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.groovybot.controller.handler.ScriptBlipHandler;
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.guice.ShellWrapper;
import com.groovybot.model.ScriptExecutionType;
import com.groovybot.persistence.ScriptExecutionEntityDao;

@Singleton
public class ScriptBlipHandlerImpl extends AbstractPrefixedEngineBlipHandler
        implements ScriptBlipHandler {

    private final GroovyEngineExecutionWrapper engineExecutionWrapper;

    @Inject
    public ScriptBlipHandlerImpl(
            final @ShellWrapper GroovyEngineExecutionWrapper engineExecutionWrapper,
            final BlipHandlerResponseStrategy responseStrategy,
            final EngineResultFormatter engineResultFormatter,
            final ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao) {
        super(ScriptBlipHandler.SCRIPT_PREFIX, responseStrategy,
                engineResultFormatter, groovyBotScriptExecutionEntityDao);
        this.engineExecutionWrapper = engineExecutionWrapper;
    }

    @Override
    protected ScriptExecutionType getScriptExecutionType() {
        return ScriptExecutionType.SCRIPT_BLIP;
    }

    @Override
    protected EngineResult executeScript(final String script) {
        return engineExecutionWrapper.execute(script);
    }

}
