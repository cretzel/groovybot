package com.groovybot;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest req,
            final HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp.getWriter().println(getResponse());
    }

    private String getResponse() {
        return "{\n\t\"profileUrl\":\"http://groovybot.appspot.com/\",\n"
                + "\t\"imageUrl\":\"http://groovybot.appspot.com/res/groovybot.gif\",\n"
                + "\t\"name\":\"GroovyBot\"\n}";
    }
}
