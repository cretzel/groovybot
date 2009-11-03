package com.groovybot.engine.result.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;

public class EngineResultFormatterImpl implements EngineResultFormatter {

    @Override
    public String format(final EngineResult result) {

        final StringBuilder b = new StringBuilder();

        final String output = result.getOutput();
        if (output != null) {
            b.append(output).append("\n");
        }

        final Throwable throwable = result.getThrowable();
        if (throwable != null) {
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw);
            throwable.printStackTrace(pw);
            b.append(sw.toString()).append("\n");
        }

        final String returnValue = result.getReturnValue();
        if (returnValue != null) {
            b.append("Result: ").append(returnValue);
        }

        return b.toString();
    }

}
