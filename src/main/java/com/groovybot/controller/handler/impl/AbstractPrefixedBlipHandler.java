package com.groovybot.controller.handler.impl;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.controller.response.impl.AppendResultInlineBlipStrategy;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.persistence.ScriptExecutionEntityDao;
import com.groovybot.persistence.impl.ScriptExecutionEntityDaoImpl;

public abstract class AbstractPrefixedBlipHandler {

    private final String prefix;
    private ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao;
    private BlipHandlerResponseStrategy responseStrategy;

    public AbstractPrefixedBlipHandler(final String prefix) {
        this.prefix = prefix;
        groovyBotScriptExecutionEntityDao = new ScriptExecutionEntityDaoImpl();
        // responseStrategy = new AppendResultBlipToWaveStrategy();
        responseStrategy = new AppendResultInlineBlipStrategy();
    }

    public void handleBlip(final RobotMessageBundle bundle, final Blip blip,
            final Event event) {
        assertStartsWithPrefix(blip);
        final String script = stripBlipPrefix(blip);

        final EngineResult result = executeScript(script);
        persistExecutionEntry(event, script, result);
        handleResult(bundle, blip, event, result);
    }

    private void assertStartsWithPrefix(final Blip blip) {
        if (!blip.getDocument().getText().startsWith(prefix)) {
            throw new IllegalArgumentException("This is not a script blip");
        }
    }

    private String stripBlipPrefix(final Blip blip) {
        final TextView document = blip.getDocument();
        final String text = document.getText();
        return text.substring(prefix.length());
    }

    private void persistExecutionEntry(final Event event, final String script,
            final EngineResult result) {
        // TODO move to service layer
        groovyBotScriptExecutionEntityDao.createBlipScriptEntry(event
                .getModifiedBy(), script);
    }

    protected abstract EngineResult executeScript(String script);

    public void handleResult(final RobotMessageBundle bundle, final Blip blip,
            final Event event, final EngineResult result) {
        responseStrategy.handleResult(bundle, blip, event, result);
    }

    public void setGroovyBotScriptExecutionEntityDao(
            final ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao) {
        this.groovyBotScriptExecutionEntityDao = groovyBotScriptExecutionEntityDao;
    }

    public void setResponseStrategy(
            final BlipHandlerResponseStrategy responseStrategy) {
        this.responseStrategy = responseStrategy;
    }

}