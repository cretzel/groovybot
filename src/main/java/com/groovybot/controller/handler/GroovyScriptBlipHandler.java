package com.groovybot.controller.handler;

import com.google.wave.api.Blip;

public interface GroovyScriptBlipHandler {

    public static final String SCRIPT_PREFIX = "!groovy";

    void handleScriptBlip(Blip blip);

}
