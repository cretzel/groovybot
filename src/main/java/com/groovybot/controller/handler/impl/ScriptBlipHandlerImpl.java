package com.groovybot.controller.handler.impl;

import com.groovybot.controller.handler.ScriptBlipHandler;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.GroovyEngineExecutionWrapperFactory;
import com.groovybot.engine.impl.GroovyEngineExecutionWrapperFactoryImpl;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.model.ScriptExecutionType;

public class ScriptBlipHandlerImpl extends AbstractPrefixedEngineBlipHandler
        implements ScriptBlipHandler {

    private GroovyEngineExecutionWrapper engineExecutionWrapper;

    public ScriptBlipHandlerImpl() {
        super(ScriptBlipHandler.SCRIPT_PREFIX);
        setEngineExecutionWrapperFactory(new GroovyEngineExecutionWrapperFactoryImpl());
    }

    @Override
    protected ScriptExecutionType getScriptExecutionType() {
        return ScriptExecutionType.SCRIPT_BLIP;
    }

    @Override
    protected EngineResult executeScript(final String script) {
        return engineExecutionWrapper.execute(script);
    }

    public void setEngineExecutionWrapperFactory(
            final GroovyEngineExecutionWrapperFactory factory) {
        engineExecutionWrapper = factory.createShellEngineWrapper();
    }

}
