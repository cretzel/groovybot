package com.groovybot.engine.impl;

import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.GroovyEngineExecutionWrapperFactory;
import com.groovybot.engine.GroovyShellEngine;
import com.groovybot.engine.GroovyTemplateEngine;

public class GroovyEngineExecutionWrapperFactoryImpl implements GroovyEngineExecutionWrapperFactory {

    private final GroovyTemplateEngine templateEngine;

    private final GroovyShellEngine shellEngine;

    public GroovyEngineExecutionWrapperFactoryImpl() {
        this.shellEngine = new GroovyShellEngineImpl();
        this.templateEngine = new GroovyTemplateEngineImpl();
    }

    /* (non-Javadoc)
     * @see com.groovybot.engine.impl.GroovyEngineExecutionWrapperFactory#createShellEngine()
     */
    public GroovyEngineExecutionWrapper createShellEngineWrapper() {
        return new GroovyEngineExecutionWrapperImpl(shellEngine);
    }

    /* (non-Javadoc)
     * @see com.groovybot.engine.impl.GroovyEngineExecutionWrapperFactory#createTemplateEngine()
     */
    public GroovyEngineExecutionWrapper createTemplateEngineWrapper() {
        return new GroovyEngineExecutionWrapperImpl(templateEngine);
    }
}
