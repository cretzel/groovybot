/**
 * 
 */
package com.groovybot.guice;

import com.google.inject.AbstractModule;
import com.groovybot.controller.GroovyBotController;
import com.groovybot.controller.handler.GadgetBlipHandler;
import com.groovybot.controller.handler.HelpBlipHandler;
import com.groovybot.controller.handler.ScriptBlipHandler;
import com.groovybot.controller.handler.TemplateBlipHandler;
import com.groovybot.controller.handler.impl.GadgetBlipHandlerImpl;
import com.groovybot.controller.handler.impl.HelpBlipHandlerImpl;
import com.groovybot.controller.handler.impl.ScriptBlipHandlerImpl;
import com.groovybot.controller.handler.impl.TemplateBlipHandlerImpl;
import com.groovybot.controller.impl.GroovyBotControllerImpl;
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.controller.response.impl.AppendResultInlineBlipStrategy;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.GroovyShellEngine;
import com.groovybot.engine.GroovyTemplateEngine;
import com.groovybot.engine.impl.GroovyShellEngineExecutionWrapperImpl;
import com.groovybot.engine.impl.GroovyShellEngineImpl;
import com.groovybot.engine.impl.GroovyTemplateEngineExecutionWrapperImpl;
import com.groovybot.engine.impl.GroovyTemplateEngineImpl;
import com.groovybot.engine.result.EngineResultFactory;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.engine.result.impl.DefaultEngineResultFactory;
import com.groovybot.engine.result.impl.EngineResultFormatterImpl;
import com.groovybot.persistence.ScriptExecutionEntityDao;
import com.groovybot.persistence.impl.ScriptExecutionEntityDaoImpl;

public final class DefaultGuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        // Engine
        bind(EngineResultFactory.class).to(DefaultEngineResultFactory.class);
        bind(EngineResultFormatter.class).to(EngineResultFormatterImpl.class);
        bind(GroovyEngineExecutionWrapper.class).annotatedWith(
                TemplateWrapper.class).to(
                GroovyTemplateEngineExecutionWrapperImpl.class);
        bind(GroovyEngineExecutionWrapper.class).annotatedWith(
                ShellWrapper.class).to(
                GroovyShellEngineExecutionWrapperImpl.class);
        bind(GroovyTemplateEngine.class).to(GroovyTemplateEngineImpl.class);
        bind(GroovyShellEngine.class).to(GroovyShellEngineImpl.class);
        bind(BlipHandlerResponseStrategy.class).to(
                AppendResultInlineBlipStrategy.class);
        // bind(BlipHandlerResponseStrategy.class).to(
        // AppendResultBlipToWaveStrategy.class);

        // TODO another Module?
        bind(ScriptExecutionEntityDao.class).to(
                ScriptExecutionEntityDaoImpl.class);

        // TODO another Module?
        bind(ScriptBlipHandler.class).to(ScriptBlipHandlerImpl.class);
        bind(TemplateBlipHandler.class).to(TemplateBlipHandlerImpl.class);
        bind(GadgetBlipHandler.class).to(GadgetBlipHandlerImpl.class);
        bind(HelpBlipHandler.class).to(HelpBlipHandlerImpl.class);
        bind(GroovyBotController.class).to(GroovyBotControllerImpl.class);
    }

}