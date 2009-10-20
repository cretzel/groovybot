package com.groovybot.controller.impl;

import java.util.logging.Logger;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;
import com.groovybot.controller.GroovyBotController;
import com.groovybot.controller.handler.GroovyScriptBlipHandler;
import com.groovybot.controller.handler.impl.ScriptBlipHandlerImpl;

public class GroovyBotControllerImpl implements GroovyBotController {

    private final GroovyScriptBlipHandler scriptBlipHandler;
    
    private transient Logger logger;

    private Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

    public GroovyBotControllerImpl() {
        scriptBlipHandler = new ScriptBlipHandlerImpl();
    }

    @Override
    public void processEvents(final RobotMessageBundle bundle) {
        getLogger().info("GroovyBotController.processEvents");

        if (bundle.wasSelfAdded()) {
            getLogger().info("GroovyBotController wasSelfAdded");
            handleSelfAdded(bundle);
        }

        for (final Event event : bundle.getEvents()) {
            if (event.getType() == EventType.BLIP_SUBMITTED) {
                getLogger().info("GroovyBotController blip submitted");
                handleBlipSumbitted(event, bundle);
            }
        }

    }

    private void handleSelfAdded(final RobotMessageBundle bundle) {
        final Wavelet wavelet = bundle.getWavelet();
        wavelet.appendBlip().getDocument().append("GroovyBot added");

    }

    private void handleBlipSumbitted(final Event event,
            final RobotMessageBundle bundle) {

        final Blip blip = event.getBlip();
        final TextView document = blip.getDocument();
        final String text = document.getText();

        if (text.startsWith(GroovyScriptBlipHandler.SCRIPT_PREFIX)) {
            scriptBlipHandler.handleScriptBlip(blip);
        }

    }

}
