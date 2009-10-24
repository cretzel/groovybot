package com.groovybot.controller.handler.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.TextView;
import com.groovybot.engine.EngineResult;
import com.groovybot.engine.EngineResultFormatter;
import com.groovybot.engine.GroovyEngine;
import com.groovybot.persistence.ScriptExecutionEntityDao;

@RunWith(JMock.class)
public class ScriptBlipHandlerImplTest {

    private ScriptBlipHandlerImpl handler;

    private GroovyEngine engineMock;

    private EngineResultFormatter resultFormatterMock;

    private Blip blipMock;

    private TextView documentMock;

    private EngineResult engineResultMock;
    
    private ScriptExecutionEntityDao daoMock;
    
    private Event eventMock;
    
    private final Mockery mockery = new Mockery();

    @Before
    public void before() {

        engineMock = mockery.mock(GroovyEngine.class);
        resultFormatterMock = mockery.mock(EngineResultFormatter.class);
        blipMock = mockery.mock(Blip.class);
        documentMock = mockery.mock(TextView.class);
        engineResultMock = mockery.mock(EngineResult.class);
        daoMock = mockery.mock(ScriptExecutionEntityDao.class);
        eventMock = mockery.mock(Event.class);
        handler = new ScriptBlipHandlerImpl();
        handler.setEngine(engineMock);
        handler.setEngineResultFormatter(resultFormatterMock);
        handler.setGroovyBotScriptExecutionEntityDao(daoMock);
    }

    @Test
    public void executeScript_GivenABlipWithPrefix_WillCallEngineWithoutPrefix()
            throws Exception {
        final String prefix = "!groovy";
        final String code = "def x = 10";
        final String blipText = prefix + code;

        mockery.checking(new Expectations() {
            {
                allowing(blipMock).getDocument();
                will(returnValue(documentMock));

                allowing(documentMock).getText();
                will(returnValue(blipText));

                one(engineMock).execute(code);
                will(returnValue(engineResultMock));
                
                allowing(eventMock).getModifiedBy();
                will(returnValue("x@y.com"));
                
                one(daoMock).createBlipScriptEntry("x@y.com", code);
            }
        });
        handler.executeScript(blipMock, eventMock);
    }
}
