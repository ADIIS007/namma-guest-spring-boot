package com.project.namma_guest.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class UtilitiesTest {
    @Test
    public void testEmail(){
        assertTrue(Utilities.isValidEmail("test1@gmail.com"));
        assertTrue(Utilities.isValidEmail("test_2@gmail.com"));
    }
    @Test
    public void testEmailWithSpaces(){
        assertFalse(Utilities.isValidEmail("test1 @gmail.com"));
        assertFalse(Utilities.isValidEmail("test1@ gmail.com"));

    }
    @Test
    public void testEmailWithoutAtTheRateOrDot(){
        assertFalse(Utilities.isValidEmail("test1gmail.com"));
        assertFalse(Utilities.isValidEmail("test1@gmailcom"));
        assertFalse(Utilities.isValidEmail("test1gmailcom"));
    }
    @Test
    public void testEmailWithWrongPosition(){
        assertFalse(Utilities.isValidEmail("test1.gmail@com"));
        assertFalse(Utilities.isValidEmail("test1gmail.@com"));
        assertFalse(Utilities.isValidEmail("test1gmail@.com"));

        }

}