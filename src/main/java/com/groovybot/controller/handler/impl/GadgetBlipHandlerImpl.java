package com.groovybot.controller.handler.impl;

import com.google.appengine.repackaged.com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.Gadget;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.controller.handler.GadgetBlipHandler;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.guice.ShellWrapper;
import com.groovybot.model.ScriptExecutionType;
import com.groovybot.persistence.ScriptExecutionEntityDao;
import com.groovybot.util.BlipUtils;
import com.groovybot.util.GroovyGadget;

@Singleton
public class GadgetBlipHandlerImpl implements GadgetBlipHandler {

    private final GroovyEngineExecutionWrapper engineWrapper;
    private final EngineResultFormatter engineResultFormatter;
    private final ScriptExecutionEntityDao scriptExecutionDao;

    @Inject
    public GadgetBlipHandlerImpl(
            final @ShellWrapper GroovyEngineExecutionWrapper engineWrapper,
            final EngineResultFormatter engineResultFormatter,
            final ScriptExecutionEntityDao scriptExecutionDao) {
        super();
        this.engineWrapper = engineWrapper;
        this.engineResultFormatter = engineResultFormatter;
        this.scriptExecutionDao = scriptExecutionDao;
    }

    @Override
    public boolean accepts(final RobotMessageBundle bundle, final Blip blip,
            final Event event) {
        return blip.getDocument().getText().startsWith(ADD_GADGET_PREFIX);
    }

    @Override
    public void handlePrefixedBlip(final RobotMessageBundle bundle,
            final Blip blip, final Event event) {
        Preconditions.checkArgument(blip.getDocument().getText().startsWith(
                ADD_GADGET_PREFIX),
                "Gadget does not start with ADD_GADGET_PREFIX");

        if (BlipUtils.getGroovyGadget(blip) == null) {
            final GroovyGadget groovyGadget = GroovyGadget.newInstance();
            groovyGadget.setInput("println 'foo'");
            final Gadget gadget = groovyGadget.getGadget();
            blip.getDocument().getGadgetView().append(gadget);
        }
    }

    @Override
    public void handleGadget(final RobotMessageBundle bundle, final Blip blip,
            final Event event) {
        final GroovyGadget groovyGadget = BlipUtils.getGroovyGadget(blip);
        Preconditions.checkNotNull(groovyGadget, "No GroovyGadget found");

        if (groovyGadget.isRunCall()) {
            final String code = groovyGadget.getInput();
            if (code != null) {
                scriptExecutionDao.createEntry(event.getModifiedBy(), code,
                        ScriptExecutionType.GADGET);
                final EngineResult result = executeScript(code);

                // TODO Replace gadget with a new instance. Workaround for bug.
                blip.getDocument().getGadgetView().delete(
                        groovyGadget.getGadget());
                final GroovyGadget newGadget = GroovyGadget.newInstance();
                blip.getDocument().getGadgetView()
                        .append(newGadget.getGadget());
                newGadget.setOutput(engineResultFormatter.format(result));
                newGadget.setInput(code);
                newGadget.resetRunCall();
            }
        }
    }

    protected EngineResult executeScript(final String script) {
        return engineWrapper.execute(script);
    }

}
