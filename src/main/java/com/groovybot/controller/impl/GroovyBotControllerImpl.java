package com.groovybot.controller.impl;

import java.util.logging.Logger;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;
import com.groovybot.controller.GroovyBotController;
import com.groovybot.controller.handler.ScriptBlipHandler;
import com.groovybot.controller.handler.TemplateBlipHandler;
import com.groovybot.controller.handler.impl.ScriptBlipHandlerImpl;
import com.groovybot.controller.handler.impl.TemplateBlipHandlerImpl;
import com.groovybot.util.BlipUtils;

public class GroovyBotControllerImpl implements GroovyBotController {

    private final ScriptBlipHandler scriptBlipHandler;
    private final TemplateBlipHandler templateBlipHandler;

    private transient Logger logger;

    private Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

    public GroovyBotControllerImpl() {
        scriptBlipHandler = new ScriptBlipHandlerImpl();
        templateBlipHandler = new TemplateBlipHandlerImpl();
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
        BlipUtils
                .appendNewBlip(
                        wavelet,
                        "GroovyBot added. Create a blip starting with "
                                + "!groovy to execute the blip as Groovy code, e.g. "
                                + "!groovy println 'Hello World', or use !gtemplate to "
                                + "execute the blip as a Groovy Template, e.g. "
                                + "!gtemplate <% out.println 'Foo' %>");
    }

    private void handleBlipSumbitted(final Event event,
            final RobotMessageBundle bundle) {

        final Blip blip = event.getBlip();
        final TextView document = blip.getDocument();
        final String text = document.getText();

        if (text.startsWith(ScriptBlipHandler.SCRIPT_PREFIX)) {
            scriptBlipHandler.handleBlip(bundle, blip, event);
        }

        if (text.startsWith(TemplateBlipHandler.TEMPLATE_PREFIX)) {
            templateBlipHandler.handleBlip(bundle, blip, event);
        }

    }

}
