package com.groovybot;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class GroovyBotApplicationFilter implements Filter {

    private GroovyBotApplication application;

    private Logger logger;

    @Override
    public void init(final FilterConfig arg0) throws ServletException {
        application = new GroovyBotApplication();
        application.init();
    }

    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res,
            final FilterChain chain) throws IOException, ServletException {
        getLogger().info("Filtering");

        GroovyBotApplication.set(application);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        application = null;
    }

    public Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("GroovyBotApplicationFilter");
        }
        return logger;
    }

}
