package com.project.namma_guest.service;

import org.springframework.stereotype.Service;

@Service
public class Utilities {
    public String generateOTP() {
        int randomPin1 = (int) (Math.random()*10);
        int randomPin2 = (int) (Math.random()*10);
        int randomPin3 = (int) (Math.random()*10);
        int randomPin4 = (int) (Math.random()*10);
        String otp = randomPin1 + "" + randomPin2 + "" + randomPin3 + "" + randomPin4;
        return otp;
    }
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) return false;
        boolean hasAtSymbol = false;
        boolean hasDot = false;
        for (int i = 0; i < email.length(); i++) {
            char ch = email.charAt(i);
            if (!Character.isLetterOrDigit(ch) && ch != '_' && ch != '@' && ch != '.') {
                return false;
            }
            if (ch == '@') {
                if (hasAtSymbol) return false;
                hasAtSymbol = true;
            }
            if (ch == '.') {
                hasDot = true;
            }
        }
        return hasAtSymbol && hasDot && email.indexOf('@') < email.lastIndexOf('.') && (email.indexOf('.') - email.lastIndexOf('@'))> 1;
    }


}
