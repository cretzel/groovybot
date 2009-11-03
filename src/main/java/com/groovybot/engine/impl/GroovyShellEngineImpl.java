package com.groovybot.engine.impl;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import com.groovybot.engine.GroovyShellEngine;

public class GroovyShellEngineImpl implements GroovyShellEngine {

    public GroovyShellEngineImpl() {
    }

    @Override
    public Object execute(final String script) {
        final GroovyShell shell = new GroovyShell(newBinding());
        return shell.evaluate(script);
    }

    private Binding newBinding() {
        final Binding binding = new Binding();
        return binding;
    }

}
