package com.groovybot.engine.impl;

import groovy.lang.Binding;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;

import com.groovybot.engine.GroovyTemplateEngine;

public class GroovyTemplateEngineImpl implements GroovyTemplateEngine {

    public GroovyTemplateEngineImpl() {
    }

    @Override
    public Object execute(final String script) {
        final SimpleTemplateEngine engine = new SimpleTemplateEngine();
        try {
            final Template template = engine.createTemplate(script);
            return template.make(newBinding().getVariables());
        } catch (final Exception e) {
            throw new RuntimeException("Failed to create template");
        }
    }

    private Binding newBinding() {
        return new Binding();
    }

}
