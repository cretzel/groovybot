package com.groovybot.controller.handler;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;

public interface TemplateBlipHandler {

    String TEMPLATE_PREFIX = "!gtemplate";

    void handleBlip(RobotMessageBundle bundle, Blip blip, Event event);

}
