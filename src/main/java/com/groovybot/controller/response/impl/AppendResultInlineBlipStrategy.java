package com.groovybot.controller.response.impl;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.util.BlipUtils;

public class AppendResultInlineBlipStrategy implements
        BlipHandlerResponseStrategy {

    public AppendResultInlineBlipStrategy() {
    }

    @Override
    public void handleResult(final RobotMessageBundle bundle, final Blip blip,
            final Event event, final String result) {
        BlipUtils.appendOrReplaceResponseInlineBlip(blip, result);
    }

}
