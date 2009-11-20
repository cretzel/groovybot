package com.groovybot.controller.handler.impl;

import com.google.inject.Inject;
import com.groovybot.controller.handler.ScriptBlipHandler;
import com.groovybot.engine.GroovyShellEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.model.ScriptExecutionType;

public class ScriptBlipHandlerImpl extends AbstractPrefixedEngineBlipHandler
        implements ScriptBlipHandler {

    private final GroovyShellEngineExecutionWrapper engineExecutionWrapper;

    @Inject
    public ScriptBlipHandlerImpl(
            final GroovyShellEngineExecutionWrapper engineExecutionWrapper) {
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
