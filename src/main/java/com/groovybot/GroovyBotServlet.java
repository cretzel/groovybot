package com.groovybot;

import java.util.logging.Logger;

import javax.servlet.ServletException;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.controller.GroovyBotController;
import com.groovybot.controller.impl.GroovyBotControllerImpl;

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
        botController = new GroovyBotControllerImpl();
    }

    @Override
    public void processEvents(final RobotMessageBundle bundle) {
        getLogger().info("GroovyBotServlet.processEvents");
        botController.processEvents(bundle);
    }

}
