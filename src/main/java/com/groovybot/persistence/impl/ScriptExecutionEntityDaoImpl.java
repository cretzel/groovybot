package com.groovybot.persistence.impl;

import javax.jdo.PersistenceManager;

import com.groovybot.model.ScriptExecutionEntity;
import com.groovybot.model.ScriptExecutionType;
import com.groovybot.persistence.PMF;
import com.groovybot.persistence.ScriptExecutionEntityDao;

public class ScriptExecutionEntityDaoImpl implements ScriptExecutionEntityDao {

    public ScriptExecutionEntity makePersistent(final ScriptExecutionEntity entry) {
        final PersistenceManager pm = getPersistenceManager();
        try {
            return getPersistenceManager().makePersistent(entry);
        } finally {
            pm.close();
        }
    }

    @Override
    public ScriptExecutionEntity createBlipScriptEntry(final String participantId,
            final String script) {
        final ScriptExecutionEntity entity = new ScriptExecutionEntity(participantId,
                script, ScriptExecutionType.SCRIPT_BLIP);
        return makePersistent(entity);
    }

    protected final PersistenceManager getPersistenceManager() {
        return PMF.getPersistanceManager();
    }

}
