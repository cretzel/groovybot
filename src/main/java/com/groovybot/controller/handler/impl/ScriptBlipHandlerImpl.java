package com.groovybot.controller.handler.impl;

import java.util.logging.Logger;

import com.google.wave.api.Blip;
import com.google.wave.api.TextView;
import com.groovybot.controller.handler.GroovyScriptBlipHandler;
import com.groovybot.engine.EngineResult;
import com.groovybot.engine.EngineResultFormatter;
import com.groovybot.engine.GroovyEngine;
import com.groovybot.engine.impl.EngineResultFormatterImpl;
import com.groovybot.engine.impl.GroovyEngineImpl;

public class ScriptBlipHandlerImpl implements GroovyScriptBlipHandler {

    private final GroovyEngine engine;

    private final EngineResultFormatter engineResultFormatter;

    private transient Logger logger;

    private Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

    public ScriptBlipHandlerImpl() {
        engine = new GroovyEngineImpl();
        engineResultFormatter = new EngineResultFormatterImpl();
    }

    @Override
    public void handleScriptBlip(final Blip blip) {
        getLogger().info("handleScriptBlip");
        final EngineResult result = executeScript(blip);
        getLogger()
                .info("handleScriptBlip.result = " + result.getReturnValue());
        handleResult(blip, result);
    }

    private EngineResult executeScript(final Blip blip) {
        final TextView document = blip.getDocument();
        final String text = document.getText();

        assert (text.startsWith(GroovyScriptBlipHandler.SCRIPT_PREFIX));

        final String script = text
                .substring(GroovyScriptBlipHandler.SCRIPT_PREFIX.length());
        return engine.execute(script);
    }

    private void handleResult(final Blip blip, final EngineResult result) {
        getLogger().info("handleResult");

        final String formattedResult = engineResultFormatter.format(result);
        TextView resultDocument;
        if (blip.hasChildren()) {
            resultDocument = blip.getChild(0).getDocument();
            resultDocument.delete();
        } else {
            resultDocument = blip.createChild().getDocument();
        }
        resultDocument.append(formattedResult);

    }
}
