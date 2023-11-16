package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    
    private static final String NAME = "Mario Rossi";
    private static final String OTHER_NAME = "FilippoBianchi";


    private DeathNote notebook;
    
    @BeforeEach
    public void setUp () {
        notebook = new DeathNoteImpl();
    }

    @Test
    public void testNonExistingRules () {
        try {
            notebook.getRule(0);
            fail("Should not be able to read rule number 0");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }

        try {
            notebook.getRule(-1);
            fail("Should not be able to read rule less than 0");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }

        try {
            notebook.getRule(DeathNote.RULES.size() + 1);
            fail("Should not be able to read rule greater than number of rules");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    

    @Test
    public void testEmptyRule() {
        for (int i = 1; i < DeathNote.RULES.size(); i++) {
            assertNotNull(notebook.getRule(i));
            assertNotEquals("", notebook.getRule(i));
        }
    }
    
    @Test
    public void testWritingName() {
        assertFalse(notebook.isNameWritten(NAME));
        notebook.writeName(NAME);
        assertTrue(notebook.isNameWritten(NAME));
        assertFalse(notebook.isNameWritten(OTHER_NAME));
        assertFalse(notebook.isNameWritten(""));
    }

    @Test
    public void testDeathCause() throws InterruptedException{
        try {
            notebook.writeDeathCause("heart attack");
            fail("Should not be able to write death cause before name");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        notebook.writeName(NAME);
        assertEquals("heart attack", notebook.getDeathCause(NAME));
        notebook.writeName(OTHER_NAME);
        assertTrue(notebook.writeDeathCause("karting accident"));
        assertEquals("karting accident", notebook.getDeathCause(OTHER_NAME));
        Thread.sleep(100);
        notebook.writeDeathCause("heart attack");
        assertEquals("karting accident", notebook.getDeathCause(OTHER_NAME));
    }

    @Test
    public void testDetails() throws InterruptedException{
        try {
            notebook.writeDetails ("ran for too long");
            fail("Should not be able to write details before name");
        } catch (IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        notebook.writeName(NAME);
        assertTrue(notebook.getDeathDetails(NAME).isEmpty());
        assertTrue(notebook.writeDetails("ran for too long"));
        assertEquals("ran for too long", notebook.getDeathDetails(NAME));
        notebook.writeName(OTHER_NAME);
        Thread.sleep(6100);
        notebook.writeDetails("eaten too much");
        assertEquals("", notebook.getDeathDetails(OTHER_NAME));
    }
}