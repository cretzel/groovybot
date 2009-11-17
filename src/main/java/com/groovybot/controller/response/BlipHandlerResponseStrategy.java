package com.groovybot.controller.response;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;

public interface BlipHandlerResponseStrategy {

    public void handleResult(final RobotMessageBundle bundle, final Blip blip,
            Event event, final String result);

}
