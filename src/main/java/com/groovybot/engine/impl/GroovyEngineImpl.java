package com.groovybot.engine.impl;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.groovybot.engine.EngineResult;
import com.groovybot.engine.GroovyEngine;

public class GroovyEngineImpl implements GroovyEngine {

    public GroovyEngineImpl() {
    }

    public EngineResult execute(final String script) {

        Object result = null;
        Throwable throwable = null;
        StackTraceElement[] stackTrace = null;
        String output = null;

        final ByteArrayOutputStream bout = new ByteArrayOutputStream();
        final PrintStream oldSysout = System.out;

        final GroovyShell shell = newShell();
        try {
            System.setOut(new PrintStream(bout));
            result = shell.evaluate(script);
        } catch (final Exception e) {
            throwable = e;
            stackTrace = e.getStackTrace();
        } finally {
            System.setOut(oldSysout);
            output = new String(bout.toByteArray());
        }

        return new DefaultEngineResult(result, output, throwable, stackTrace);
    }

    private GroovyShell newShell() {
        return new GroovyShell(newBinding());
    }

    private Binding newBinding() {
        final Binding binding = new Binding();
        return binding;
    }

    private static final class DefaultEngineResult implements EngineResult {

        private final String returnValue;
        private final String output;
        private final StackTraceElement[] stackTrace;
        private final Throwable throwable;

        public DefaultEngineResult(final Object returnValue,
                final String output, final Throwable throwable,
                final StackTraceElement[] stackTrace) {
            this.output = output;
            this.returnValue = returnValue == null ? null : returnValue
                    .toString();
            this.throwable = throwable;
            this.stackTrace = stackTrace;
        }

        @Override
        public String getReturnValue() {
            return returnValue;
        }

        public Throwable getThrowable() {
            return throwable;
        }

        public StackTraceElement[] getStackTrace() {
            return stackTrace;
        }

        public String getOutput() {
            return output;
        }

    }

}
