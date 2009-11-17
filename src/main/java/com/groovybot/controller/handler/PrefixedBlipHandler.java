package com.groovybot.controller.handler;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;

public interface PrefixedBlipHandler {

    boolean accepts(final RobotMessageBundle bundle, final Blip blip,
            final Event event);

    public void handlePrefixedBlip(final RobotMessageBundle bundle, final Blip blip,
            final Event event);

}
