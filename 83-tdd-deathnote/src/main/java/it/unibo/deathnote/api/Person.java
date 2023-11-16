package it.unibo.deathnote.api;

public interface Person {
    
    public String getName();

    public String getDeathCause();

    public String getDeathDetails();

    public boolean setDeathCause(String cause);

    public boolean setDeathDetails(String details);
}
