package com.groovybot.persistence.impl;

import javax.jdo.PersistenceManager;

import com.google.inject.Singleton;
import com.groovybot.model.ScriptExecutionEntity;
import com.groovybot.model.ScriptExecutionType;
import com.groovybot.persistence.PMF;
import com.groovybot.persistence.ScriptExecutionEntityDao;

@Singleton
public class ScriptExecutionEntityDaoImpl implements ScriptExecutionEntityDao {

    public ScriptExecutionEntity makePersistent(
            final ScriptExecutionEntity entry) {
        final PersistenceManager pm = getPersistenceManager();
        try {
            return getPersistenceManager().makePersistent(entry);
        } finally {
            pm.close();
        }
    }

    @Override
    public ScriptExecutionEntity createEntry(final String participantId,
            final String script, final ScriptExecutionType type) {
        final ScriptExecutionEntity entity = new ScriptExecutionEntity(
                participantId, script, type);
        return makePersistent(entity);
    }

    protected final PersistenceManager getPersistenceManager() {
        return PMF.getPersistanceManager();
    }

}
