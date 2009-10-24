package com.groovybot.persistence;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

public final class PMF {

    private static final PersistenceManagerFactory pmfInstance = JDOHelper
            .getPersistenceManagerFactory("transactions-optional");

    private PMF() {
    }

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }

    public static PersistenceManager getPersistanceManager() {
        return get().getPersistenceManager();
    }
}