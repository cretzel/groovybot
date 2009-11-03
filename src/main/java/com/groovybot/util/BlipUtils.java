package com.groovybot.util;

import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.wave.api.Blip;
import com.google.wave.api.Wavelet;
import com.groovybot.GroovyBotApplication;

public final class BlipUtils {
    private static transient Logger logger;

    private static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

    private BlipUtils() {
    }

    public static Blip appendNewBlip(final Wavelet wavelet, final String text) {
        final Blip blip = wavelet.appendBlip();
        // TODO
        // Workaraound for http://code.google.com/p/google-wave-resources/issues/detail?id=354&sort=-id&colspec=Stars%20ID%20Type%20Status%20Priority%20Milestone%20Owner%20Summary%20Internal
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

    public static List<Blip> getThisRobotsInlineBlips(final Blip blip) {
        return Lists.newArrayList(Iterables.filter(blip.getDocument()
                .getInlineBlips(), new Predicate<Blip>() {

            @Override
            public boolean apply(final Blip blip) {
                return GroovyBotApplication.ROBOT_EMAIL.equals(blip
                        .getCreator());
            }

        }));
    }

}
