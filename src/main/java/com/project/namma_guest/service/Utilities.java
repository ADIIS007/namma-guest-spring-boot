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
    public boolean isValidEmail(String email) {
        return true;
//        return email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }
}
