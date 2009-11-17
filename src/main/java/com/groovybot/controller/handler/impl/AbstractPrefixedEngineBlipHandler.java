package com.groovybot.controller.handler.impl;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.controller.response.impl.AppendResultInlineBlipStrategy;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.engine.result.impl.EngineResultFormatterImpl;
import com.groovybot.model.ScriptExecutionType;
import com.groovybot.persistence.ScriptExecutionEntityDao;
import com.groovybot.persistence.impl.ScriptExecutionEntityDaoImpl;

public abstract class AbstractPrefixedEngineBlipHandler extends
        AbstractPrefixedBlipHandler {

    private ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao;
    private BlipHandlerResponseStrategy responseStrategy;
    private EngineResultFormatter engineResultFormatter;

    public AbstractPrefixedEngineBlipHandler(final String prefix) {
        super(prefix);
        groovyBotScriptExecutionEntityDao = new ScriptExecutionEntityDaoImpl();
        // responseStrategy = new AppendResultBlipToWaveStrategy();
        responseStrategy = new AppendResultInlineBlipStrategy();
        engineResultFormatter = new EngineResultFormatterImpl();
    }

    @Override
    protected void handleBlipContent(final RobotMessageBundle bundle,
            final Blip blip, final Event event, final String script) {
        final EngineResult result = executeScript(script);
        persistExecutionEntry(event, script, result);
        handleResult(bundle, blip, event, result);
    }

    private void persistExecutionEntry(final Event event, final String script,
            final EngineResult result) {
        // TODO move to service layer
        groovyBotScriptExecutionEntityDao.createEntry(event.getModifiedBy(),
                script, getScriptExecutionType());
    }

    protected abstract ScriptExecutionType getScriptExecutionType();

    protected abstract EngineResult executeScript(String script);

    public void handleResult(final RobotMessageBundle bundle, final Blip blip,
            final Event event, final EngineResult result) {
        final String formattedResult = engineResultFormatter.format(result);
        responseStrategy.handleResult(bundle, blip, event, formattedResult);
    }

    public void setGroovyBotScriptExecutionEntityDao(
            final ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao) {
        this.groovyBotScriptExecutionEntityDao = groovyBotScriptExecutionEntityDao;
    }

    public void setResponseStrategy(
            final BlipHandlerResponseStrategy responseStrategy) {
        this.responseStrategy = responseStrategy;
    }

    public void setEngineResultFormatter(
            final EngineResultFormatter engineResultFormatter) {
        this.engineResultFormatter = engineResultFormatter;
    }

}
