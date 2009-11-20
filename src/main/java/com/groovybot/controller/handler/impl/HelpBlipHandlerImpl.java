package com.groovybot.controller.handler.impl;

import com.google.inject.Singleton;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.controller.handler.HelpBlipHandler;
import com.groovybot.util.BlipUtils;

@Singleton
public class HelpBlipHandlerImpl extends AbstractPrefixedBlipHandler implements
        HelpBlipHandler {

    public HelpBlipHandlerImpl() {
        super(HelpBlipHandler.HELP_PREFIX);
    }

    @Override
    protected void handleBlipContent(final RobotMessageBundle bundle,
            final Blip blip, final Event event, final String script) {
        final String text = "GroovyBot Help\n\n" + "Start a blip with ...\n\n"
                + "!groovy to execute the blip as Groovy code, e.g. "
                + "!groovy println 'Hello World'\n\n"
                + "!gtemplate to execute the blip as a Groovy Template, e.g. "
                + "!gtemplate <% out.println 'Foo' %>\n\n"
                + "!ggadget to add a nice Groovy gadget";
        BlipUtils.appendOrReplaceResponseInlineBlip(blip, text);
    }
}
