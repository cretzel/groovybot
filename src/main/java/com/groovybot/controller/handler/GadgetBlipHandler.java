package com.groovybot.controller.handler;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;

public interface GadgetBlipHandler extends PrefixedBlipHandler {

    public static final String ADD_GADGET_PREFIX = "!ggadget";

    public void handleGadget(RobotMessageBundle bundle, Blip blip,
            Event event);

}
