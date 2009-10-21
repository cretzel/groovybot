package com.groovybot.controller.handler.impl;

import java.util.logging.Logger;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.Gadget;
import com.google.wave.api.GadgetView;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;
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
    public void handleScriptBlip(final RobotMessageBundle bundle,
            final Event event, final Blip blip) {
        getLogger().info("handleScriptBlip");
        final EngineResult result = executeScript(blip);
        getLogger()
                .info("handleScriptBlip.result = " + result.getReturnValue());
        handleResult(bundle, event, blip, result);
    }

    private EngineResult executeScript(final Blip blip) {
        final TextView document = blip.getDocument();
        final String text = document.getText();

        assert (text.startsWith(GroovyScriptBlipHandler.SCRIPT_PREFIX));

        final String script = text
                .substring(GroovyScriptBlipHandler.SCRIPT_PREFIX.length());
        return engine.execute(script);
    }

    private void handleResult(final RobotMessageBundle bundle,
            final Event event, final Blip blip, final EngineResult result) {
        getLogger().info("handleResult");

        final String formattedResult = engineResultFormatter.format(result);

        // TODO only one of these variants
        // handleResultUpdateCreateChildBlip(blip, formattedResult);
        handleResultUpdateCreateOutputGadget(blip, formattedResult);
        // handleResultAddChildBlipToWavelet(blip, formattedResult);
        // handleResultAddWaveletToWave(bundle, formattedResult);
    }

    private void handleResultUpdateCreateOutputGadget(final Blip blip,
            final String formattedResult) {
        final GadgetView gadgetView = blip.getDocument().getGadgetView();
        final String url = "http://groovybot.appspot.com/gadgets/groovybot_output.xml";

        final Gadget gadget = new Gadget(url);
        if (gadgetView.getGadget(url) != null) {
            gadgetView.delete(url);
        }
        gadgetView.append(gadget);
        gadget.setField("output", formattedResult);
    }

    private void handleResultAddWaveletToWave(final RobotMessageBundle bundle,
            final String formattedResult) {
        final Wavelet w = bundle.createWavelet(bundle.getWavelet()
                .getParticipants());
        w.appendBlip().getDocument().append(formattedResult);
    }

    private void handleResultAddChildBlipToWavelet(final Blip blip,
            final String formattedResult) {
        final Wavelet wavelet = blip.getWavelet();
        final Blip resultBlip = wavelet.appendBlip();
        final TextView document = resultBlip.getDocument();
        document.append(formattedResult);
    }

    private void handleResultUpdateCreateChildBlip(final Blip blip,
            final String formattedResult) {
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
