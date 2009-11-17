package com.groovybot.persistence;

import com.groovybot.model.ScriptExecutionEntity;
import com.groovybot.model.ScriptExecutionType;

public interface ScriptExecutionEntityDao {

    ScriptExecutionEntity makePersistent(ScriptExecutionEntity entry);

    ScriptExecutionEntity createEntry(String participantId, String script,
            ScriptExecutionType type);

}
