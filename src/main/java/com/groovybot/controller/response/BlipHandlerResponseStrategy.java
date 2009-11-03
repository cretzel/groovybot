package com.groovybot.controller.response;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.engine.result.EngineResult;

public interface BlipHandlerResponseStrategy {

    public void handleResult(final RobotMessageBundle bundle, final Blip blip,
            Event event, final EngineResult result);

}
