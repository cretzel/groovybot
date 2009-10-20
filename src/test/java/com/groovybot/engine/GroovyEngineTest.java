package com.groovybot.engine;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import org.codehaus.groovy.control.CompilationFailedException;
import org.junit.Before;
import org.junit.Test;

import com.groovybot.engine.impl.GroovyEngineImpl;

public class GroovyEngineTest {

    private GroovyEngine engine;

    @Before
    public void before() {
        engine = new GroovyEngineImpl();
    }

    @Test
    public void engineShouldExecuteSimpleScript() throws Exception {
        final String script = "def tmp = 'Hello World'";
        engine.execute(script);
    }

    @Test
    public void engineShouldReturnReturnValueOfScript() throws Exception {
        final String script = "def x = 'Hello World'\nreturn x";
        final EngineResult result = engine.execute(script);

        assertNotNull(result);
        assertEquals("Hello World", result.getReturnValue());
    }

    @Test
    public void engineShouldReturnNullStringOnNoReturnValue() throws Exception {
        final String script = "def x = 'Hello World'\nprintln 'X'";
        final EngineResult result = engine.execute(script);

        assertNull(result.getReturnValue());
    }

    @Test
    public void engineShouldReturnExceptionOnCompilationFailure() throws Exception {
        final String script = "}cla Foo";
        final EngineResult result = engine.execute(script);

        assertTrue(result.getThrowable() instanceof CompilationFailedException);
    }

    @Test
    public void engineShouldReturnStacktraceOnCompilationFailure() throws Exception {
        final String script = "}cla Foo";
        final EngineResult result = engine.execute(script);

        assertNotNull(result.getStackTrace());
        assertTrue(result.getStackTrace().length > 0);
    }

    @Test
    public void engineShouldReturnStacktraceOnExecutionFailure() throws Exception {
        final String script = "def x = n";
        final EngineResult result = engine.execute(script);

        assertNotNull(result.getStackTrace());
        assertTrue(result.getStackTrace().length > 0);
    }

    @Test
    public void engineShouldReturnStdoutAsString() throws Exception {
        final String script = "println 'Foo'";
        final EngineResult result = engine.execute(script);

        assertNotNull(result.getOutput());
        assertEquals("Foo\r\n", result.getOutput());
    }

}
