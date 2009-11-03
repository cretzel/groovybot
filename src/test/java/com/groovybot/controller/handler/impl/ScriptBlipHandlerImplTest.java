package com.groovybot.controller.handler.impl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.wave.api.Blip;
import com.google.wave.api.Event;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.GroovyEngineExecutionWrapperFactory;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.persistence.ScriptExecutionEntityDao;

@RunWith(JMock.class)
public class ScriptBlipHandlerImplTest {

    private final Mockery mockery = new Mockery();
    private ScriptBlipHandlerImpl handler;
    private EngineResultFormatter resultFormatterMock;
    private Blip blipMock;
    private TextView documentMock;
    private EngineResult engineResultMock;
    private ScriptExecutionEntityDao daoMock;
    private Event eventMock;
    private GroovyEngineExecutionWrapperFactory engineExecutionWrapperFactoryMock;
    private GroovyEngineExecutionWrapper engineWrapperMock;
    private RobotMessageBundle bundleMock;

    @Before
    public void before() {

        resultFormatterMock = mockery.mock(EngineResultFormatter.class);
        blipMock = mockery.mock(Blip.class);
        bundleMock = mockery.mock(RobotMessageBundle.class);
        documentMock = mockery.mock(TextView.class);
        engineResultMock = mockery.mock(EngineResult.class);
        daoMock = mockery.mock(ScriptExecutionEntityDao.class);
        eventMock = mockery.mock(Event.class);
        engineExecutionWrapperFactoryMock = mockery
                .mock(GroovyEngineExecutionWrapperFactory.class);
        engineWrapperMock = mockery.mock(GroovyEngineExecutionWrapper.class);
        handler = new ScriptBlipHandlerImpl();

        mockery.checking(new Expectations() {
            {
                one(engineExecutionWrapperFactoryMock)
                        .createShellEngineWrapper();
                will(returnValue(engineWrapperMock));

            }
        });
        handler
                .setEngineExecutionWrapperFactory(engineExecutionWrapperFactoryMock);

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

                one(engineWrapperMock).execute(code);
                will(returnValue(engineResultMock));

                allowing(eventMock);
                allowing(daoMock);
                allowing(resultFormatterMock);
                allowing(bundleMock);
            }
        });
        handler.handleBlip(bundleMock, blipMock, eventMock);
    }

    @Test
    public void executeScript_GivenABlipWithPrefix_WillCallResultFormatter()
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
                allowing(engineWrapperMock).execute(with(any(String.class)));
                will(returnValue(engineResultMock));
                allowing(eventMock);
                allowing(daoMock);

                allowing(resultFormatterMock).format(engineResultMock);
                allowing(bundleMock);
            }
        });
        handler.handleBlip(bundleMock, blipMock, eventMock);
    }

}
