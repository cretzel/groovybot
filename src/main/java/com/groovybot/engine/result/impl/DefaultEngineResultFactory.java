package com.groovybot.engine.result.impl;

import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFactory;

public class DefaultEngineResultFactory implements EngineResultFactory {

    public EngineResult create(final Object returnValue, final String output,
            final Throwable throwable, final StackTraceElement[] stackTrace) {
        return new DefaultEngineResult(returnValue, output, throwable,
                stackTrace);
    }

}
