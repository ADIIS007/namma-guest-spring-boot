package com.project.namma_guest.service;

import com.project.namma_guest.helper.MailService;
import com.project.namma_guest.helper.Utilities;
import com.project.namma_guest.model.Users;
import com.project.namma_guest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class UserService {
    private final UsersRepository usersRepository;
    private final MailService mailService;
    @Autowired
    public UserService(UsersRepository userRepository, MailService mailService) {
        this.usersRepository = userRepository;
        this.mailService = mailService;
    }

    @Transactional
    public ResponseEntity<String> sendOtp(String email) throws IOException {
        //TODO Send email otp to the user
        // Step 0 - Validate the Incoming data weather null or no make a function in helper folder & validation Class
        // Step 1 - Check the email is valid (must have @ ect) - 400 Bad Request
        // Step 2 - Check that there is a user with the following - 404 Not Found
        // Step 3 - OTP was not sent at lest 2 min ago - 429 Too Many Requests
        // Step 4 - SEND OTP & time before it cant resend - 200 OK

        boolean isEmailValid = Utilities.isValidEmail(email);
        if (isEmailValid) {
            Users user = usersRepository.findUsersByEmail(email);
            String otp = Utilities.generateOTP();
            if(user!=null) {
                Date date = user.getOTPGeneratedAt();
                if (date!= null && (date.getTime() + 60 * 1000) > System.currentTimeMillis()) {
                    throw new IOException("OTP was sent at least 2 minutes ago");
                }
                user.setOTP(otp);
                user.setOTPGeneratedAt(new java.util.Date());
                usersRepository.save(user);
                log.info("User already exists, updating OTP");
            } else {
                user = new Users();
                user.setOTP(otp);
                user.setEmail(email);
                user.setOTPGeneratedAt(new java.util.Date());
                usersRepository.save(user);
                log.info("New User created with OTP");
            }
            mailService.sendMail(email, "OTP for Namma Guest", "Your OTP for Namma Guest is: " + otp);
            log.info("OTP sent to {} OTP: {}", email, otp);
            return new ResponseEntity<>("OTP sent to " + email, HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("Invalid Email Address");
        }
    }

    @Transactional
    public ResponseEntity<String> verifyOtp(String email, String otp) throws TimeoutException, IOException {
        //TODO Verification of OTP sent for email given
        // Step 0 - Validate the Incoming data weather null or no make a function in helper folder & validation Class
        // Step 1 - Check the email is valid (must have @ ect) - 400 Bad Request
        // Step 2 - Check that there is a user with the following - 404 Not Found
        // Step 3 - OTP was sent at lest 2 min ago - 410 Gone/400 Bad Request
        // Step 4 - SEND OTP & time before it cant resend - 200 OK
        boolean isEmailValid = Utilities.isValidEmail(email);
        if(isEmailValid) {
            Users user = usersRepository.findUsersByEmail(email);
            if (user != null) {
                Date date = user.getOTPGeneratedAt();
                if (date != null && (date.getTime() + 5 * 60 * 1000) >= System.currentTimeMillis()) {
                    if (user.getOTP().equals(otp)) {
                        if(user.getUserType() == null) {
                            return ResponseEntity.ok("TODO");
                        } else {
                            return ResponseEntity.ok(user.getUserType().toUpperCase());
                        }
                    } else {
                        throw new IOException("Invalid OTP");
                    }
                } else {
                    throw new TimeoutException("OTP Expired");
                }
            } else {
                throw new NullPointerException("No user found with given OTP");
            }
        } else {
            throw new IllegalArgumentException("Invalid Email Address");
        }
    }

    @Transactional
    public ResponseEntity<String> resendOtp(String email) throws Exception {
        //TODO: This is to resend the OTP for given email
        // Step 0 - Validate the Incoming data weather null or no make a function in helper folder & validation Class
        // Step 1 - Check the email is valid (must have @ ect) - 400 Bad Request
        // Step 2 - Check that there is a user with the following - 404 Not Found
        // Step 3 - OTP was not sent at lest 2 min ago - 429 Too Many Requests
        // Step 4 - SEND OTP & time before it cant resend again - 200 OK
        if(Utilities.isValidEmail(email)) {
            Users user = usersRepository.findUsersByEmail(email);
            if(user!= null) {
                if((user.getOTPGeneratedAt().getTime() + 2 *60 * 1000) < (System.currentTimeMillis())) {
                    String otp = Utilities.generateOTP();
                    user.setOTP(otp);
                    user.setOTPGeneratedAt(new java.util.Date());
                    mailService.sendMail(email, "OTP for Namma Guest", "Your OTP for Namma Guest is: " + otp);
                    usersRepository.save(user);
                    return new ResponseEntity<>("OTP sent to " + email, HttpStatus.OK);
                } else {
                    throw new TimeoutException("Wait for 2 min before retrying");
                }
            } else {
                throw new NullPointerException("No user found with given email");
            }
        } else {
            throw new IllegalArgumentException("Invalid Email Address");
        }
    }

    public ResponseEntity<String> setUserType(String type, String email) {
        //TODO: This is to resend the OTP for given email
        // Step 0 - Validate the Incoming data weather null or no make a function in helper folder & validation Class
        // Step 1 - Validate the type parameter must ber user or owner - 400 Bad Request
        // Step 2 - Check the email is valid (must have @ ect) - 400 Bad Request
        // Step 3 - Check that there is a user with the following - 404 Not Found
        // Step 4 - Check that user is not a user or owner already - 409 Conflict
        // Step 5 - set the user type to
        if(Utilities.isValidEmail(email)){
            Users user = usersRepository.findUsersByEmail(email);
            if(user!= null) {
                if(user.getUserType()!=null) {
                    if (type.equalsIgnoreCase("USER") || type.equalsIgnoreCase("OWNER")) {
                        user.setUserType(type.toUpperCase());
                        usersRepository.save(user);
                        return ResponseEntity.ok("User Type updated successfully");
                    } else {
                        throw new IllegalArgumentException("Invalid Type of usertype to set");
                    }
                } else {
                    throw new IllegalArgumentException("User is already a specified");
                }
            } else {
                throw new NullPointerException("No user found with given email");
            }
        } else {
            throw new IllegalArgumentException("Invalid Email Address");
        }
    }
}
