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
    @Test
    public void testPhoneNumber(){
        assertTrue(Utilities.isPhoneValid("9638527101"));
        assertTrue(Utilities.isPhoneValid("9638527112"));
    }

    @Test
    public void testPhoneNumberWithSpaces(){
        assertFalse(Utilities.isPhoneValid("96385 27101"));
        assertFalse(Utilities.isPhoneValid("963 8527 101"));
    }

    @Test
    public void testPhoneNumberWithSpecialCharacters(){
        assertFalse(Utilities.isPhoneValid("9638@27101"));
        assertFalse(Utilities.isPhoneValid("9638@271_0"));
        assertFalse(Utilities.isPhoneValid("**********"));
    }

    @Test
    public void testPhoneNumberWithIncorrectSize(){
        assertFalse(Utilities.isPhoneValid("963857112"));
        assertFalse(Utilities.isPhoneValid("96322857112"));
    }
}