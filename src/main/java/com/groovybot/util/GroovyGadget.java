package com.groovybot.util;

import com.google.appengine.repackaged.com.google.common.base.Preconditions;
import com.google.wave.api.Gadget;

public class GroovyGadget {

    public static final String GADGET_URL = "http://groovybot.appspot.com/gadgets/groovybot_gadget.xml";

    private static final String OUTPUT_FIELD = "output";

    private static final String INPUT_FIELD = "code";

    private static final String RUN_FIELD = "run";

    private final Gadget gadget;

    private GroovyGadget(final Gadget gadget) {
        this.gadget = gadget;
    }

    public static GroovyGadget newInstance() {
        return new GroovyGadget(new Gadget(GroovyGadget.GADGET_URL));
    }

    public static GroovyGadget fromGadget(final Gadget gadget) {
        Preconditions.checkArgument(GADGET_URL.equals(gadget.getUrl()),
                "Gadget URL must equal " + GADGET_URL);
        return new GroovyGadget(gadget);
    }

    public void setOutput(final String output) {
        gadget.setField(OUTPUT_FIELD, output);
    }

    public String getInput() {
        return gadget.getField(INPUT_FIELD);
    }

    public void setInput(final String code) {
        gadget.setField(INPUT_FIELD, code);
    }

    public boolean isRunCall() {
        return "true".equals(gadget.getField(RUN_FIELD));
    }

    public void resetRunCall() {
        gadget.setField(RUN_FIELD, "false");
    }

    public Gadget getGadget() {
        return gadget;
    }

}
