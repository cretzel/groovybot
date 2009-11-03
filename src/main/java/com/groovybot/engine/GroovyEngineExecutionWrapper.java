package com.groovybot.engine;

import com.groovybot.engine.result.EngineResult;

public interface GroovyEngineExecutionWrapper {

    EngineResult execute(String script);

}