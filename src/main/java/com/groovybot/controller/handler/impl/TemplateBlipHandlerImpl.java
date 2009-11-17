package com.groovybot.controller.handler.impl;

import com.groovybot.controller.handler.TemplateBlipHandler;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.GroovyEngineExecutionWrapperFactory;
import com.groovybot.engine.impl.GroovyEngineExecutionWrapperFactoryImpl;
import com.groovybot.engine.result.EngineResult;

public class TemplateBlipHandlerImpl extends AbstractPrefixedEngineBlipHandler
        implements TemplateBlipHandler {

    private GroovyEngineExecutionWrapper engineExecutionWrapper;

    public TemplateBlipHandlerImpl() {
        super(TemplateBlipHandler.TEMPLATE_PREFIX);
        setEngineExecutionWrapperFactory(new GroovyEngineExecutionWrapperFactoryImpl());
    }

    @Override
    protected EngineResult executeScript(final String script) {
        return engineExecutionWrapper.execute(script);
    }

    public void setEngineExecutionWrapperFactory(
            final GroovyEngineExecutionWrapperFactory factory) {
        engineExecutionWrapper = factory.createTemplateEngineWrapper();
    }

}
