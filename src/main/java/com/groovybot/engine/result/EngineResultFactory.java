package com.groovybot.engine.result;


public interface EngineResultFactory {

    EngineResult create(final Object returnValue, final String output,
            final Throwable throwable, final StackTraceElement[] stackTrace);

}