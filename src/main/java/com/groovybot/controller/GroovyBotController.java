package com.groovybot.controller;

import com.google.wave.api.RobotMessageBundle;

public interface GroovyBotController {

    void processEvents(RobotMessageBundle bundle);

}
