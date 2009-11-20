package com.groovybot.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class ScriptExecutionEntity {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long id;

    @Persistent
    private String participantId;

    @Persistent()
    private Text script;

    @Persistent
    private Date executionDate;

    @Persistent
    private ScriptExecutionType type;

    public ScriptExecutionEntity(final String participantId, final String script,
            final ScriptExecutionType type) {
        super();
        this.participantId = participantId;
        this.script = new Text(script);
        this.type = type;
        this.executionDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(final String participantId) {
        this.participantId = participantId;
    }

    public Text getScript() {
        return script;
    }

    public void setScript(final Text script) {
        this.script = script;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(final Date executionDate) {
        this.executionDate = executionDate;
    }

    public ScriptExecutionType getType() {
        return type;
    }

    public void setType(final ScriptExecutionType type) {
        this.type = type;
    }

}
