package com.project.namma_guest.helper;

import com.project.namma_guest.model.Users;
import com.project.namma_guest.service.MailService;
import com.project.namma_guest.service.PayingGuestService;
import com.project.namma_guest.service.UserService;
import com.project.namma_guest.service.Utilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationHelper {
    private final PayingGuestService payingGuestService;
    private final UserService userService;
    private final Utilities utilities;
    private final MailService mailService;
    public AuthenticationHelper(PayingGuestService payingGuestService, UserService usersService, Utilities utilities, MailService mailService) {
        this.payingGuestService = payingGuestService;
        this.userService = usersService;
        this.utilities = utilities;
        this.mailService = mailService;
    }
    public ResponseEntity<String> sendOtp(String email) {
        boolean isEmailValid = utilities.isValidEmail(email);
        if (isEmailValid) {
            String otp = utilities.generateOTP();
            if(userService.existsByEmail(email)) {
                userService.updateOTP(email, otp);
                log.info("User already exists, updating OTP");
            } else {
                userService.createNewUser(email, otp);
                log.info("New User created with OTP");
            }
            mailService.sendMail(email, "OTP for Namma Guest", "Your OTP for Namma Guest is: " + otp);
            log.info("OTP sent to " + email + " OTP: " + otp);
            return new ResponseEntity("OTP sent to " + email, HttpStatus.OK);
        } else {
            return new ResponseEntity("Invalid Email", HttpStatus.OK);
        }
    }
    public ResponseEntity<String> verifyOtp(String email, String otp) {
        Users user = userService.verifyOTP(email, otp);
        if(user != null) {
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to send email to " + email, HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<String> resendOtp(String email) {
        String otp = utilities.generateOTP();
        userService.updateOTP(email, otp);
        return new ResponseEntity("OTP sent to " + email,HttpStatus.OK);
    }
    public ResponseEntity<String> phoneNumberValidation(String contactNumber) {
        boolean isPhoneNumberValid = Utilities.isPhoneValid(contactNumber);
        if(isPhoneNumberValid){
            log.info("Phone number is valid");
            return new ResponseEntity<>("Phone number is valid", HttpStatus.OK);
        }
        else{
            log.info("Phone number is not valid");
            return new ResponseEntity<>("Phone number is not valid", HttpStatus.BAD_REQUEST);
        }
    }
}
