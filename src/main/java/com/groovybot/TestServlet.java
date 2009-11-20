package com.groovybot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.groovybot.controller.GroovyBotController;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.guice.ShellWrapper;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EngineResultFormatter formatter;
    private GroovyEngineExecutionWrapper engineWrapper;

    @Override
    public void init() throws ServletException {
        super.init();
        final Injector injector = GroovyBotApplication.get().getInjector();
        engineWrapper = injector.getInstance(Key.get(
                GroovyEngineExecutionWrapper.class, ShellWrapper.class));
        formatter = injector.getInstance(EngineResultFormatter.class);
    }

    @Override
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");

        final String script = "println 'Foo'";
        final EngineResult result = engineWrapper.execute(script);
        resp.getWriter().println(result.getOutput());
    }

    @Override
    protected void doPost(final HttpServletRequest req,
            final HttpServletResponse resp) throws ServletException,
            IOException {
        GroovyBotApplication.get().getLogger().info("TestServlet.doPost");

        if (req.getParameter("execute") != null) {
            final String input = req.getParameter("input");
            final EngineResult result = engineWrapper.execute(input);
            resp.getWriter().println(formatter.format(result));
        }

        final GroovyBotController botController = GroovyBotApplication.get()
                .getInjector().getInstance(GroovyBotController.class);

        System.err.println("foo");

    }

}
