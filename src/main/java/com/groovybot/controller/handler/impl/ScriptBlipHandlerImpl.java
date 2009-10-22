package com.groovybot.controller.handler.impl;

import java.util.logging.Logger;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.groovybot.controller.handler.GroovyScriptBlipHandler;
import com.groovybot.engine.EngineResult;
import com.groovybot.engine.EngineResultFormatter;
import com.groovybot.engine.GroovyEngine;
import com.groovybot.engine.impl.EngineResultFormatterImpl;
import com.groovybot.engine.impl.GroovyEngineImpl;
import com.groovybot.persistence.ScriptExecutionEntityDao;
import com.groovybot.persistence.impl.ScriptExecutionEntityDaoImpl;
import com.groovybot.util.BlipUtils;

public class ScriptBlipHandlerImpl implements GroovyScriptBlipHandler {

    private GroovyEngine engine;

    private EngineResultFormatter engineResultFormatter;

    private ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao;

    private transient Logger logger;

    public ScriptBlipHandlerImpl() {
        engine = new GroovyEngineImpl();
        engineResultFormatter = new EngineResultFormatterImpl();
        groovyBotScriptExecutionEntityDao = new ScriptExecutionEntityDaoImpl();
    }

    @Override
    public void handleScriptBlip(final RobotMessageBundle bundle,
            final Blip blip, final Event event) {
        assertStartsWithPrefix(blip);

        final EngineResult result = executeScript(blip, event);
        handleResult(bundle, blip, result);
    }

    private void assertStartsWithPrefix(final Blip blip) {
        if (!blip.getDocument().getText().startsWith(
                GroovyScriptBlipHandler.SCRIPT_PREFIX)) {
            throw new IllegalArgumentException("This is not a script blip");
        }
    }

    EngineResult executeScript(final Blip blip, final Event event) {
        final TextView document = blip.getDocument();
        final String text = document.getText();

        final String script = text
                .substring(GroovyScriptBlipHandler.SCRIPT_PREFIX.length());

        // TODO move to service layer
        groovyBotScriptExecutionEntityDao.createBlipScriptEntry(event
                .getModifiedBy(), script);

        return engine.execute(script);
    }

    void handleResult(final RobotMessageBundle bundle, final Blip blip,
            final EngineResult result) {
        final String formattedResult = engineResultFormatter.format(result);
        BlipUtils.appendNewBlip(bundle.getWavelet(), formattedResult);
        // final Gadget gadget = OutputGadget.getOrCreateInBlip(blip);
        // OutputGadget.setOutput(gadget, formattedResult);
    }

    Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

    void setEngine(final GroovyEngine engine) {
        this.engine = engine;
    }

    void setEngineResultFormatter(
            final EngineResultFormatter engineResultFormatter) {
        this.engineResultFormatter = engineResultFormatter;
    }

    void setGroovyBotScriptExecutionEntityDao(
            final ScriptExecutionEntityDao groovyBotScriptExecutionEntityDao) {
        this.groovyBotScriptExecutionEntityDao = groovyBotScriptExecutionEntityDao;
    }

}
