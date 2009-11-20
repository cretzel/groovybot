package com.groovybot.controller.handler.impl;

import com.google.inject.Inject;
import com.groovybot.controller.handler.ScriptBlipHandler;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.guice.ShellWrapper;
import com.groovybot.model.ScriptExecutionType;

public class ScriptBlipHandlerImpl extends AbstractPrefixedEngineBlipHandler
        implements ScriptBlipHandler {

    private final GroovyEngineExecutionWrapper engineExecutionWrapper;

    @Inject
    public ScriptBlipHandlerImpl(
            final @ShellWrapper GroovyEngineExecutionWrapper engineExecutionWrapper) {
        super(ScriptBlipHandler.SCRIPT_PREFIX);
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
