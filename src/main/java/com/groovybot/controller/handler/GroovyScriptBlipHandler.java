package com.groovybot.controller.handler;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;

public interface GroovyScriptBlipHandler {

    public static final String SCRIPT_PREFIX = "!groovy";

    void handleScriptBlip(RobotMessageBundle bundle, Event event, Blip blip);

}
