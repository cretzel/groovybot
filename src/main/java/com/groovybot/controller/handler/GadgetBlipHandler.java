package com.groovybot.controller.handler;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;

public interface GadgetBlipHandler {

    public static final String ADD_GADGET_PREFIX = "!gGadget";

    public void handleBlip(final RobotMessageBundle bundle, final Blip blip,
            final Event event);

    public void handleAddGadget(RobotMessageBundle bundle, Blip blip,
            Event event);

}
