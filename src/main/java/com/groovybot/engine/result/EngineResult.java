package com.groovybot.engine.result;

public interface EngineResult {

    String getReturnValue();

    StackTraceElement[] getStackTrace();

    Throwable getThrowable();

    String getOutput();

}
