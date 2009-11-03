package com.groovybot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.impl.GroovyEngineExecutionWrapperFactoryImpl;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.engine.result.impl.EngineResultFormatterImpl;

public class TestServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private GroovyEngineExecutionWrapper engineWrapper;
    private EngineResultFormatter formatter;

    @Override
    public void init() throws ServletException {
        super.init();
        final GroovyEngineExecutionWrapperFactoryImpl engineWrapperFactory = new GroovyEngineExecutionWrapperFactoryImpl();
        engineWrapper = engineWrapperFactory.createShellEngineWrapper();
        formatter = new EngineResultFormatterImpl();
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

        if (req.getParameter("execute") != null) {
            final String input = req.getParameter("input");
            final EngineResult result = engineWrapper.execute(input);
            resp.getWriter().println(formatter.format(result));
        }
        
    }

}
