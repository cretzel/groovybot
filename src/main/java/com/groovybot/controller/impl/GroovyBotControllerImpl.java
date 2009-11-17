package com.groovybot.controller.impl;

import java.util.List;
import java.util.logging.Logger;

import com.google.inject.internal.Lists;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;
import com.groovybot.controller.GroovyBotController;
import com.groovybot.controller.handler.GadgetBlipHandler;
import com.groovybot.controller.handler.HelpBlipHandler;
import com.groovybot.controller.handler.PrefixedBlipHandler;
import com.groovybot.controller.handler.ScriptBlipHandler;
import com.groovybot.controller.handler.TemplateBlipHandler;
import com.groovybot.controller.handler.impl.GadgetBlipHandlerImpl;
import com.groovybot.controller.handler.impl.HelpBlipHandlerImpl;
import com.groovybot.controller.handler.impl.ScriptBlipHandlerImpl;
import com.groovybot.controller.handler.impl.TemplateBlipHandlerImpl;
import com.groovybot.util.BlipUtils;
import com.groovybot.util.GroovyGadget;

public class GroovyBotControllerImpl implements GroovyBotController {

    private final ScriptBlipHandler scriptBlipHandler;
    private final TemplateBlipHandler templateBlipHandler;
    private final GadgetBlipHandler gadgetBlipHandler;
    private final HelpBlipHandler helpBlipHandler;
    private List<PrefixedBlipHandler> prefixedBlipHandlers = Lists
            .newArrayList();

    private transient Logger logger;

    public GroovyBotControllerImpl() {
        scriptBlipHandler = new ScriptBlipHandlerImpl();
        templateBlipHandler = new TemplateBlipHandlerImpl();
        gadgetBlipHandler = new GadgetBlipHandlerImpl();
        helpBlipHandler = new HelpBlipHandlerImpl();
        prefixedBlipHandlers = Lists.newArrayList(scriptBlipHandler,
                templateBlipHandler, gadgetBlipHandler, helpBlipHandler);
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
        BlipUtils.appendNewBlip(wavelet, "GroovyBot added. Create a "
                + "blip starting with !ghelp for help.");
    }

    private void handleBlipSumbitted(final Event event,
            final RobotMessageBundle bundle) {

        final Blip blip = event.getBlip();

        // PrefixedBlipHandlers
        for (final PrefixedBlipHandler prefixedBlipHandler : prefixedBlipHandlers) {
            if (prefixedBlipHandler.accepts(bundle, blip, event)) {
                prefixedBlipHandler.handlePrefixedBlip(bundle, blip, event);
            }
        }

        // GadgetHandler
        final TextView document = blip.getDocument();
        if (document.getGadgetView().getGadget(GroovyGadget.GADGET_URL) != null) {
            gadgetBlipHandler.handleGadget(bundle, blip, event);
        }

    }

    private Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

}
