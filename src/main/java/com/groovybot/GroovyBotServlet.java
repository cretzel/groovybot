package com.groovybot;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.controller.GroovyBotController;

@SuppressWarnings("serial")
public class GroovyBotServlet extends AbstractRobotServlet {

    private GroovyBotController botController;

    private transient Logger logger;

    private Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBot");
        }
        return logger;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        botController = GroovyBotApplication.get().getInjector().getInstance(
                GroovyBotController.class);
    }

    @Override
    public void processEvents(final RobotMessageBundle bundle) {
        getLogger().info("GroovyBotServlet.processEvents");
        botController.processEvents(bundle);
    }

}
