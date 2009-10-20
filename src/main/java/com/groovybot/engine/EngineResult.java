package com.groovybot.engine;

public interface EngineResult {

    String getReturnValue();

    StackTraceElement[] getStackTrace();

    Throwable getThrowable();

    String getOutput();

}
