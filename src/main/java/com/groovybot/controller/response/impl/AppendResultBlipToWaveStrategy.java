package com.groovybot.controller.response.impl;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.engine.result.impl.EngineResultFormatterImpl;
import com.groovybot.util.BlipUtils;

public class AppendResultBlipToWaveStrategy implements
        BlipHandlerResponseStrategy {

    private EngineResultFormatter engineResultFormatter;

    public AppendResultBlipToWaveStrategy() {
        engineResultFormatter = new EngineResultFormatterImpl();
    }

    @Override
    public void handleResult(final RobotMessageBundle bundle, final Blip blip,
            final Event event, final EngineResult result) {
        final String formattedResult = engineResultFormatter.format(result);
        BlipUtils.appendNewBlip(bundle.getWavelet(), formattedResult);
    }

    public void setEngineResultFormatter(
            final EngineResultFormatter engineResultFormatter) {
        this.engineResultFormatter = engineResultFormatter;
    }

}
