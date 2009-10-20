package com.groovybot;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groovybot.engine.EngineResult;
import com.groovybot.engine.GroovyEngine;
import com.groovybot.engine.impl.GroovyEngineImpl;

public class TestServlet extends HttpServlet {

    
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("Hello, world");

        final String script = "println 'Foo'";
        
        final GroovyEngine engine = new GroovyEngineImpl();
        final EngineResult result = engine.execute(script);
        resp.getWriter().println(result.getOutput());
    }

}
