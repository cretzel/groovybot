package com.groovybot.engine;


public interface GroovyEngineExecutionWrapperFactory {

    GroovyEngineExecutionWrapper createShellEngineWrapper();

    GroovyEngineExecutionWrapper createTemplateEngineWrapper();

}