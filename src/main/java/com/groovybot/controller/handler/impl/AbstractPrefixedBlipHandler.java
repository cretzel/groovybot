package com.groovybot.controller.handler.impl;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.groovybot.controller.handler.PrefixedBlipHandler;

public abstract class AbstractPrefixedBlipHandler implements
        PrefixedBlipHandler {

    private final String prefix;

    public AbstractPrefixedBlipHandler(final String prefix) {
        this.prefix = prefix;
    }

    @Override
    public boolean accepts(final RobotMessageBundle bundle, final Blip blip,
            final Event event) {
        return startsWithPrefix(blip);
    }

    public void handlePrefixedBlip(final RobotMessageBundle bundle, final Blip blip,
            final Event event) {
        assertStartsWithPrefix(blip);
        final String script = stripBlipPrefix(blip);

        handleBlipContent(bundle, blip, event, script);
    }

    protected abstract void handleBlipContent(final RobotMessageBundle bundle,
            final Blip blip, final Event event, final String script);

    private void assertStartsWithPrefix(final Blip blip) {
        if (!startsWithPrefix(blip)) {
            throw new IllegalArgumentException("This is not a script blip");
        }
    }

    private boolean startsWithPrefix(final Blip blip) {
        return blip.getDocument().getText().startsWith(prefix);
    }

    private String stripBlipPrefix(final Blip blip) {
        final TextView document = blip.getDocument();
        final String text = document.getText();
        return text.substring(prefix.length());
    }

}