package com.groovybot.util;

import com.google.wave.api.Blip;
import com.google.wave.api.Gadget;
import com.google.wave.api.GadgetView;

public class OutputGadget {

    private static final String OUTPUT_GADGET_URL = "http://groovybot.appspot.com/gadgets/groovybot_output.xml";

    private static final String OUTPUT_FIELD = "output";

    private OutputGadget() {
        throw new UnsupportedOperationException();
    }

    public static void setOutput(final Gadget gadget, final String output) {
        gadget.setField(OutputGadget.OUTPUT_FIELD, output);
    }

    public static Gadget createGadget() {
        return new Gadget(OutputGadget.OUTPUT_GADGET_URL);
    }

    public static Gadget getOrCreateInBlip(final Blip blip) {
        final GadgetView gadgetView = blip.getDocument().getGadgetView();

        if (gadgetView.getGadget(OutputGadget.OUTPUT_GADGET_URL) != null) {
            gadgetView.delete(OutputGadget.OUTPUT_GADGET_URL);
        }

        final Gadget gadget = createGadget();
        gadgetView.append(gadget);
        return gadget;
    }

}
