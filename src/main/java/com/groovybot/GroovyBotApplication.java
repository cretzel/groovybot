package com.groovybot;

import java.util.logging.Logger;

public final class GroovyBotApplication {

    public static final String ROBOT_EMAIL = "groovybot@appspot.com";
    private static transient Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

}
