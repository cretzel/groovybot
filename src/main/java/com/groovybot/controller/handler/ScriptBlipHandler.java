package com.groovybot.controller.handler;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;

public interface ScriptBlipHandler {

    public static final String SCRIPT_PREFIX = "!groovy";

    public void handleBlip(final RobotMessageBundle bundle,
            final Blip blip, final Event event);

}
