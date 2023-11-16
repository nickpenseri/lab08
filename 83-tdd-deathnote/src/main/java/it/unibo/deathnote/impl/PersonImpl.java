package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.Person;

public class PersonImpl implements Person{

    private static final String DEFAULT_DEATH_CAUSE = "heart attack";
    private static final long TIME_TO_WRITE_CAUSE = 40L;
    private static final long TIME_TO_WRITE_DETAILS = 6040L;

    private final String name;
    private String details;
    private String deathCause;
    private final long timeWritingName;
    private long timeWritingCause;

    public PersonImpl(String name) {
        this.name = name;
        this.deathCause =DEFAULT_DEATH_CAUSE;
        this.timeWritingName = System.currentTimeMillis();
        this.timeWritingCause = System.currentTimeMillis();
        this.details = "";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDeathCause() {
        return this.deathCause;
    }

    @Override
    public String getDeathDetails() {
        return this.details;
    }

    @Override
    public boolean setDeathCause(String cause) {
        if (this.canWriteCause()) {
            this.deathCause = cause;
            this.timeWritingCause = System.currentTimeMillis();
            return true;
        } else {
            return false;
        }
    }

    private boolean canWriteCause () {
        return (System.currentTimeMillis() - this.timeWritingName) < TIME_TO_WRITE_CAUSE;
    }

    @Override
    public boolean setDeathDetails(String details) {
        if (this.canWriteDetails()) {
            this.details = details;
            return true;
        } else {
            return false;
        }
    }

    private boolean canWriteDetails () {
        return (System.currentTimeMillis() - this.timeWritingCause) < TIME_TO_WRITE_DETAILS;
    }
    
}
