package com.groovybot.util;

import java.util.List;

import com.google.appengine.repackaged.com.google.common.base.Predicate;
import com.google.appengine.repackaged.com.google.common.collect.Iterables;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.wave.api.Blip;

public final class BlipUtils {

    public static final String ROBOT_EMAIL = "groovybot@appspot.com";

    private BlipUtils() {
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
                return ROBOT_EMAIL.equals(blip.getCreator());
            }

        }));
    }

}
