package com.groovybot.guice;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.groovybot.engine.GroovyEngine;
import com.groovybot.engine.GroovyShellEngine;

public class BasicGuiceTest {

    private Injector injector;

    @Before
    public void before() {
        injector = Guice.createInjector(new DefaultGuiceModule());

    }

    @Test
    public void itsOnlySingletonIfImplementationIsAnnotatedWithSingleton()
            throws Exception {
        final GroovyEngine instance1 = injector
                .getInstance(GroovyShellEngine.class);
        final GroovyEngine instance2 = injector
                .getInstance(GroovyShellEngine.class);

        System.err.println(instance1.hashCode());
        System.err.println(instance2.hashCode());
    }
}
