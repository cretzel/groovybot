package com.groovybot.util;

import java.util.List;

import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.wave.api.Blip;
import com.google.wave.api.Gadget;
import com.google.wave.api.GadgetView;
import com.google.wave.api.Wavelet;
import com.groovybot.GroovyBotApplication;

public final class BlipUtils {

    private BlipUtils() {
    }

    public static Blip appendNewBlip(final Wavelet wavelet, final String text) {
        final Blip blip = wavelet.appendBlip();
        // TODO
        // Workaraound for
        // http://code.google.com/p/google-wave-resources/issues/detail?id=354&sort=-id&colspec=Stars%20ID%20Type%20Status%20Priority%20Milestone%20Owner%20Summary%20Internal
        blip.getDocument().delete();
        blip.getDocument().append(text);
        return blip;
    }

    public static void removeInlineBlipsOfThisRobot(final Blip blip) {
        final List<Blip> myInlineBlips = getThisRobotsInlineBlips(blip);
        for (final Blip inlineBlip : myInlineBlips) {
            blip.getDocument().deleteInlineBlip(inlineBlip);
        }
    }

    /* TODO: Does never find any inline blips although there are some. */
    public static List<Blip> getThisRobotsInlineBlips(final Blip blip) {
        GroovyBotApplication.getLogger().info("getThisRobotsInlineBlips: ");
        return Lists.newArrayList(Iterables.filter(blip.getDocument()
                .getInlineBlips(), new Predicate<Blip>() {

            @Override
            public boolean apply(final Blip blip) {
                GroovyBotApplication.getLogger().info(
                        "creator: " + blip.getCreator());
                return GroovyBotApplication.ROBOT_EMAIL.equals(blip
                        .getCreator());
            }

        }));
    }

    public static Blip appendOrReplaceResponseInlineBlip(final Blip blip,
            final String text) {
        final List<Blip> thisRobotsInlineBlips = getThisRobotsInlineBlips(blip);
        if (thisRobotsInlineBlips.isEmpty()) {
            return appendNewInlineBlip(blip, text);
        } else {
            final Blip inlineBlip = thisRobotsInlineBlips.get(0);
            inlineBlip.getDocument().replace(text);
            return inlineBlip;
        }
    }

    private static Blip appendNewInlineBlip(final Blip blip, final String text) {
        final Blip inlineBlip = blip.getDocument().appendInlineBlip();
        inlineBlip.getDocument().append(text);
        return inlineBlip;
    }

    public static GroovyGadget getOrAppendToBlip(final Blip blip) {
        GroovyGadget groovyGadget = getGroovyGadget(blip);

        if (groovyGadget == null) {
            groovyGadget = GroovyGadget.newInstance();
            final GadgetView gadgetView = blip.getDocument().getGadgetView();
            gadgetView.append(groovyGadget.getGadget());
            return groovyGadget;
        }

        return groovyGadget;
    }

    public static GroovyGadget getGroovyGadget(final Blip blip) {
        final Gadget gadget = blip.getDocument().getGadgetView().getGadget(
                GroovyGadget.GADGET_URL);
        return gadget != null ? GroovyGadget.fromGadget(gadget) : null;
    }

}
