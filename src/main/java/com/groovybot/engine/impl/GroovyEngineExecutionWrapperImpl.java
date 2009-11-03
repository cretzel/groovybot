package com.groovybot.engine.impl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.google.apphosting.api.ApiProxy;
import com.google.apphosting.api.ApiProxy.Environment;
import com.groovybot.engine.GroovyEngine;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.impl.DefaultEngineResult;

public class GroovyEngineExecutionWrapperImpl implements
        GroovyEngineExecutionWrapper {

    private final GroovyEngine engine;

    public GroovyEngineExecutionWrapperImpl(final GroovyEngine engine) {
        this.engine = engine;
    }

    public EngineResult execute(final String script) {

        Object result = null;
        Throwable throwable = null;
        StackTraceElement[] stackTrace = null;
        String output = null;

        final Environment oldAppEngineEnv = ApiProxy.getCurrentEnvironment();
        final PrintStream oldSysout = System.out;
        final PrintStream oldSyserr = System.err;
        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final PrintStream sysPrintStream = new PrintStream(bout, true);

        try {
            ApiProxy.clearEnvironmentForCurrentThread();
            System.setOut(sysPrintStream);
            System.setErr(sysPrintStream);
            result = engine.execute(script);
        } catch (final Exception e) {
            throwable = e;
            stackTrace = e.getStackTrace();
        } finally {
            ApiProxy.setEnvironmentForCurrentThread(oldAppEngineEnv);
            System.setOut(oldSysout);
            System.setErr(oldSyserr);
            output = bout.toString();
        }

        return new DefaultEngineResult(result, output, throwable, stackTrace);
    }

}