package it.unibo.deathnote.impl;

import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.api.Person;

public class DeathNoteImpl implements DeathNote{

    private String lastNameWritten;
    private Map<String, Person> deathMap;

    public DeathNoteImpl() {
        this.lastNameWritten = null;
        this.deathMap = new HashMap<>();
    }

    @Override
    public String getRule(int ruleNumber) {
        if (!isRuleAccepted(ruleNumber)) {
            throw new IllegalArgumentException("Rule must be between 1 and" + DeathNote.RULES.size());
        } else {
            return DeathNote.RULES.get(ruleNumber - 1);
        }
    }

    private static boolean isRuleAccepted (int ruleNumber) {
        return ruleNumber >= 1 && ruleNumber <= DeathNote.RULES.size();
    }

    @Override
    public void writeName(String name) {
        if (name == null) {
            throw new NullPointerException("Name mustn't be null");
        } else {
            this.deathMap.put(name, new PersonImpl(name));
            this.lastNameWritten = name;
        }
    }

    @Override
    public boolean writeDeathCause(String cause) {
        if (cause == null) {
            throw new IllegalStateException("cause mustn't be null");
        }
        if (lastNameWritten == null) {
            throw new IllegalStateException("No names written in this death note");
        }
        return this.deathMap.get(this.lastNameWritten).setDeathCause(cause);
    }

    @Override
    public boolean writeDetails(String details) {
        if (details == null) {
            throw new IllegalStateException("details mustn't be null");
        }
        if (lastNameWritten == null) {
            throw new IllegalStateException("No names written in this death note");
        }
        return this.deathMap.get(this.lastNameWritten).setDeathDetails(details);
    }

    @Override
    public String getDeathCause(String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException("Name is no written in the death note");
        } else {
            return this.deathMap.get(name).getDeathCause();
        }
    }

    @Override
    public String getDeathDetails(String name) {
        if (!this.isNameWritten(name)) {
            throw new IllegalArgumentException("Name is not written in this death note");
        } else {
            return this.deathMap.get(name).getDeathDetails();
        }
    }

    @Override
    public boolean isNameWritten(String name) {
        return this.deathMap.keySet().contains(name);
    }
}
