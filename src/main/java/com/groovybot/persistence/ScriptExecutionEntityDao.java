package com.groovybot.persistence;

import com.groovybot.model.ScriptExecutionEntity;

public interface ScriptExecutionEntityDao {

    ScriptExecutionEntity makePersistent(ScriptExecutionEntity entry);

    ScriptExecutionEntity createBlipScriptEntry(String participantId, String script);

}
