/**
 * 
 */
package com.groovybot.engine.result.impl;

import com.groovybot.engine.result.EngineResult;

public final class DefaultEngineResult implements EngineResult {

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