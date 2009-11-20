package com.groovybot;

import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.groovybot.guice.DefaultGuiceModule;

public final class GroovyBotApplication {

    public static final String ROBOT_EMAIL = "groovybot@appspot.com";

    private static final ThreadLocal<GroovyBotApplication> holder = new ThreadLocal<GroovyBotApplication>();

    private transient Logger logger;

    private Injector injector;

    public GroovyBotApplication() {
    }

    public void init() {
        injector = Guice.createInjector(new DefaultGuiceModule());
    }

    public Injector getInjector() {
        return injector;
    }

    public static void set(final GroovyBotApplication application) {
        holder.set(application);
    }

    public static GroovyBotApplication get() {
        return holder.get();
    }

    public Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

}
