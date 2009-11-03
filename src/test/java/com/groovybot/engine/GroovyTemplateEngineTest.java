package com.groovybot.engine;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.groovybot.engine.impl.GroovyTemplateEngineImpl;

public class GroovyTemplateEngineTest {

    private GroovyTemplateEngineImpl engine;

    @Before
    public void before() {
        engine = new GroovyTemplateEngineImpl();
    }

    @Test
    public void engineShouldExecuteSimpleTemplate() throws Exception {
        final String script = "def x ) ";
        engine.execute(script);
    }

    @Test
    public void engineShouldNotEvaluateScripts() throws Exception {
        final String script = "def x df= 'Hello World'";
        final Object result = engine.execute(script);

        assertNotNull(result);
        assertEquals(script, result.toString());
    }

    @Test
    public void engineShouldReturnEvaluatedTemplate() throws Exception {
        final String script = "<% def x = 'x' %><%= x %>";
        final Object result = engine.execute(script);

        assertNotNull(result);
        assertEquals("x", result.toString());
    }

    @Test
    public void engineShouldReturnEvaluatedTemplate1() throws Exception {
        final String script = "<% def x = 'x'; 4.times {%><%= x %><% } %>";
        final Object result = engine.execute(script);

        assertNotNull(result);
        assertEquals("xxxx", result.toString());
    }


}
