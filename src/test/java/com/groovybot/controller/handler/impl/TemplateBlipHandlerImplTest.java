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
import com.groovybot.controller.response.BlipHandlerResponseStrategy;
import com.groovybot.engine.GroovyEngineExecutionWrapper;
import com.groovybot.engine.result.EngineResult;
import com.groovybot.engine.result.EngineResultFormatter;
import com.groovybot.persistence.ScriptExecutionEntityDao;

@RunWith(JMock.class)
public class TemplateBlipHandlerImplTest {

    private final Mockery mockery = new Mockery();
    private TemplateBlipHandlerImpl handler;
    private EngineResultFormatter resultFormatterMock;
    private Blip blipMock;
    private TextView documentMock;
    private EngineResult engineResultMock;
    private ScriptExecutionEntityDao daoMock;
    private Event eventMock;
    private GroovyEngineExecutionWrapper engineWrapperMock;
    private RobotMessageBundle bundleMock;
    private BlipHandlerResponseStrategy responseStrategyMock;

    @Before
    public void before() {

        resultFormatterMock = mockery.mock(EngineResultFormatter.class);
        blipMock = mockery.mock(Blip.class);
        bundleMock = mockery.mock(RobotMessageBundle.class);
        documentMock = mockery.mock(TextView.class);
        engineResultMock = mockery.mock(EngineResult.class);
        daoMock = mockery.mock(ScriptExecutionEntityDao.class);
        eventMock = mockery.mock(Event.class);
        engineWrapperMock = mockery.mock(GroovyEngineExecutionWrapper.class);
        responseStrategyMock = mockery.mock(BlipHandlerResponseStrategy.class);
        handler = new TemplateBlipHandlerImpl(engineWrapperMock,
                responseStrategyMock, resultFormatterMock, daoMock);

    }

    @Test
    public void executeTemplate_GivenABlipWithPrefix_WillCallEngineWithoutPrefix()
            throws Exception {
        final String prefix = "!gtemplate";
        final String code = "<% def x = 10 %>";
        final String blipText = prefix + code;

        mockery.checking(new Expectations() {
            {
                allowing(blipMock).getDocument();
                will(returnValue(documentMock));

                allowing(documentMock).getText();
                will(returnValue(blipText));

                one(engineWrapperMock).execute(code);
                will(returnValue(engineResultMock));

                one(responseStrategyMock).handleResult(with(bundleMock),
                        with(blipMock), with(eventMock),
                        with(any(String.class)));

                allowing(eventMock);
                allowing(engineResultMock);
                allowing(daoMock);
                allowing(resultFormatterMock);
                allowing(bundleMock);
                allowing(resultFormatterMock);
            }
        });
        handler.handlePrefixedBlip(bundleMock, blipMock, eventMock);
    }

    @Test
    public void executeScript_GivenABlipWithPrefix_WillCallResponseStrategy()
            throws Exception {
        final String prefix = "!gtemplate";
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
                allowing(engineResultMock);
                allowing(resultFormatterMock);

                one(responseStrategyMock).handleResult(with(bundleMock),
                        with(blipMock), with(eventMock),
                        with(any(String.class)));
                allowing(bundleMock);
            }
        });
        handler.handlePrefixedBlip(bundleMock, blipMock, eventMock);
    }

}
